package com.kh.hehyeop.company.model.service;


import java.util.List;

import org.springframework.stereotype.Service;

import com.kh.hehyeop.common.code.Field;
import com.kh.hehyeop.common.push.PushSender;
import com.kh.hehyeop.common.util.address.AddressUtil;
import com.kh.hehyeop.common.util.file.FileUtil;
import com.kh.hehyeop.common.util.paging.Paging;
import com.kh.hehyeop.company.model.dto.CompanyField;
import com.kh.hehyeop.company.model.dto.MyRequest;
import com.kh.hehyeop.company.model.dto.RequestDetail;
import com.kh.hehyeop.company.model.repository.CompanyRepository;
import com.kh.hehyeop.company.validator.ResponseForm;
import com.kh.hehyeop.help.model.dto.HelpRequest;
import com.kh.hehyeop.member.model.dto.Member;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService{
	
	private final CompanyRepository companyRepository;
	private final PushSender pushSender;

	@Override
	public List<HelpRequest> selectRequestList(Paging paging, List<String> addressList, List<CompanyField> companyFieldList, String area) {
			
		List<HelpRequest> requestList = companyRepository.selectRequestList(paging,addressList,companyFieldList,area);
		
		AddressUtil util = new AddressUtil();
		for (HelpRequest helpRequest : requestList) {
			helpRequest.setField(Field.getField(helpRequest.getField()).fullName);
			helpRequest.setOldAddress(util.getDoSiAddress(helpRequest.getOldAddress()));
			helpRequest.setReqTime(helpRequest.getReqTime().substring(0, helpRequest.getReqTime().indexOf("T")));
		}

		return requestList;
	}
	
	@Override
	public RequestDetail selectRequestDetailByReqIdx(String reqIdx) {
		return companyRepository.selectRequestDetailByReqIdx(reqIdx);
	}
	
	public List<MyRequest> selectRequestListById(Paging paging, String id, String state) {
		//state(ongoing)로 받아온 리스트
		List<MyRequest> requestList = companyRepository.selectRequestListById(paging, id,state);
		
		for (MyRequest myRequest: requestList) {
			myRequest.setField(Field.getField(myRequest.getField()).fullName);
			myRequest.setOldAddress(convertAddress(myRequest.getOldAddress()));
			myRequest.setReqTime(convertTime(myRequest.getReqTime()));
			myRequest.setStatus(setStatus(state,myRequest));
		}
		return requestList;
	}
	
	//ex) 서울시 강남구 역삼동 까지만 반환
	private String convertAddress(String oldAddress) {
		String[] tempArr = oldAddress.split(" ");
		String address = tempArr[0] + " " + tempArr[1] + " " + tempArr[2];
		return address;
	}
	@Override
	public int countRequest(List<String> addressList, List<CompanyField> companyFieldList, String area) {
		return companyRepository.countRequest(addressList, companyFieldList, area);
	}

	@Override
	public List<CompanyField> selectCompanyFieldListById(String id) {
		return companyRepository.selectCompanyFieldListById(id);
	}

	//reqTime에 T없애주기
	private String convertTime(String reqTime) {
		String[] tempArr = reqTime.split("T");
		String time = tempArr[0] + " " + tempArr[1];
		return time;
	}
	//status 설정해주기
	private int setStatus(String state, MyRequest helpRequest) {
		int status = 0;
		int reqOngoing = helpRequest.getOngoing();
		//업체 대기상태(0) //요청 대기 중
		if(state.equals("0")) status = 0;
		
		//업체 진행상태(1) //완료,취소 버튼
		if(state.equals("1") && reqOngoing == 1) status = 1;
		if(state.equals("1") && reqOngoing == 2) status = 1;
		if(state.equals("1") && reqOngoing == 3) status = 1;
		
		//업체 완료상태(2)
		//신청자 진행상태(1) //완료 대기 중
		if(state.equals("2") && reqOngoing == 1) status = 2;
		//신청자 완료상태(2) //완료됨
		if(state.equals("2") && reqOngoing == 2) status = 3;
		
		//업체 취소상태(3)
		//신청자 미취소시(1) //취소 대기 중
		if(state.equals("3") && reqOngoing == 1) status = 4;
		//신청자 취소(3) //취소됨
		if(state.equals("3") && reqOngoing == 3) status = 5;
		
		return status;
	}

	public int selectRequestListCntById(String id, String state) {
		return companyRepository.selectRequestListCntById(id,state);
	}

	@Override
	public int completeCashByReqIdx(String id, String reqIdx) {
		//ongoing 2로 바꿔주기
		int state = 2;
		companyRepository.updateOngoing(id,reqIdx,state);
		//신청자가 이미 완료한 상태라면 최종완료 절차(업체한테 돈 넣어주기)
		int ongoing = companyRepository.selectOngoingByReqIdx(reqIdx);
		if(ongoing == 2) {
			int resPay = companyRepository.selectResPayByReqIdx(reqIdx);
			//업체 cash + res_pay
			companyRepository.completeCashByReqIdx(id,resPay);
			return 0; //서비스가 완료되었습니다.
		//신청자가 취소 상태라면 양쪽을 대기중 상태로 바꿔주기	
		}else if(ongoing == 3) {
			List<MyRequest> requestList = companyRepository.selectDisMatchRequestListById(id,state);
			for (MyRequest myRequest: requestList) {
				companyRepository.updateRequestOngoing(id);
				companyRepository.updateResponseOngoing(id);
			}
			return 1; //매칭상태가 올바르지 않습니다. 다시 선택해주세요.
		}else {
			return 2; //완료 대기 중입니다.
		}
	}

	@Override
	public int cancelCashByReqIdx(String id, String reqIdx) {
		//ongoing 3로 바꿔주기
		int state = 3;
		companyRepository.updateOngoing(id,reqIdx,state);
		//신청자가 이미 취소인 상태이면 최종취소 절차(신청자의 cash_lock - res_pay, cash + res_pay로 이동시키기)
		HelpRequest res = companyRepository.selectIdAndOngoingByReqIdx(reqIdx);
		String reqId = res.getId();
		int ongoing = res.getOngoing();
		if(ongoing == 3) {
			int resPay = companyRepository.selectResPayByReqIdx(reqIdx);
			companyRepository.cancelCashByReqIdx(reqId,resPay);
			return 0; //서비스가 취소되었습니다.
		//신청자가 완료 상태라면 양쪽을 대기중 상태로 바꿔주기		
		}else if(ongoing == 2) {
			List<MyRequest> requestList = companyRepository.selectDisMatchRequestListById(id,state);
			for (MyRequest myRequest: requestList) {
				companyRepository.updateRequestOngoing(id);
				companyRepository.updateResponseOngoing(id);
			}
			return 1; //매칭상태가 올바르지 않습니다. 다시 선택해주세요.
		}else {
			return 2; //취소 대기 중입니다.
		}
	}

	@Override
	public void insertHelpResponse(ResponseForm form, String reqIdx, String id) {
		companyRepository.insertHelpResponse(form, reqIdx, id);
		//currval 작동 이상하면 주석 해제하기
		//String resIdx = companyRepository.selectResIdxByReqIdxAndId(reqIdx, id);
		
		FileUtil util = new FileUtil();
		companyRepository.insertFileInfo(util.fileUpload(form.getFiles().get(0), null));
		
		String reqId = companyRepository.selectReqIdByReqIdx(reqIdx);
		Member member = new Member();
		member.setId(reqId);
		pushSender.send(member, "자취해협", "견적 도착! 업체가 보낸 견적을 확인해보세요.");
	}


}
