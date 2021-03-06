package com.kh.hehyeop.help.model.dto;

import java.sql.Date;

import lombok.Data;

@Data
public class HelpRequest {

	private String reqIdx;
	private String id;
	private String reqName;
	private String reqTell;
	private String reqAddress;
	private String oldAddress;
	private String reqTime;
	private int reqPay;
	private String reqContent;
	private Date regDate;
	private String field;
	private int ongoing;
}
