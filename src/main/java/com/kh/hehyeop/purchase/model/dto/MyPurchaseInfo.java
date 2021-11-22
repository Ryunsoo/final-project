package com.kh.hehyeop.purchase.model.dto;

import java.util.Date;

import lombok.Data;

@Data
public class MyPurchaseInfo {
	
	private String regIdx;
	private String matchIdx;
	private String joinIdx;
	private String id;
	private String itemName;
	private String itemLink;
	private String dealLoc;
	private String endTime;
	private String dealTime;
	private int price;
	private int totalNum;
	private int buyNum;
	private String content;
	private String done;
	private int restNum;
	private int ongoing;
}
