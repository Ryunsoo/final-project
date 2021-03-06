package com.kh.hehyeop.admin.model.dto;

import java.util.Date;

import com.kh.hehyeop.member.model.dto.User;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class CMember extends User {
	private String id;
	private String password;
	private String name;
	private String tell;
	private String email;
	private String company;
	private String address;
	private String oldAddress;
	private int point;
	private String grade;
	private Date regDate;
	private int isLeave;
	private int isPermit;
	private Date permitDate;
	private String cIdx;
	private String parseDate;//permitDate
	private int isModify;
	private Date modifyDate;
	private String modifyParseDate;
	private String regParseDate;

	public CMember() {
		super();
	}

	public CMember(String id, String name, String email, String nickname, String address, String oldAddress,
			String grade) {
		super(id, name, email, nickname, address, oldAddress, grade);
	}

}