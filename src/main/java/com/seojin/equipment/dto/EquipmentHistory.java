package com.seojin.equipment.dto;

import java.io.Serializable;

public class EquipmentHistory implements Serializable {
	private static final long serialVersionUID = 1L;

	private int hno;
	private String historyDate;
	private String companyName;
	private String stickerNo;
	private String productName;
	private String manager;
	private String actionType;
	private String partnerName;
	private String detail;

	public EquipmentHistory() {
	}

	public EquipmentHistory(int hno, String historyDate, String companyName, String stickerNo, String productName,
			String manager, String actionType, String partnerName, String detail) {
		this.hno = hno;
		this.historyDate = historyDate;
		this.companyName = companyName;
		this.stickerNo = stickerNo;
		this.productName = productName;
		this.manager = manager;
		this.actionType = actionType;
		this.partnerName = partnerName;
		this.detail = detail;
	}

	public int getHno() {
		return hno;
	}

	public void setHno(int hno) {
		this.hno = hno;
	}

	public String getHistoryDate() {
		return historyDate;
	}

	public void setHistoryDate(String historyDate) {
		this.historyDate = historyDate;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getStickerNo() {
		return stickerNo;
	}

	public void setStickerNo(String stickerNo) {
		this.stickerNo = stickerNo;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public String getActionType() {
		return actionType;
	}

	public void setActionType(String actionType) {
		this.actionType = actionType;
	}

	public String getPartnerName() {
		return partnerName;
	}

	public void setPartnerName(String partnerName) {
		this.partnerName = partnerName;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	@Override
	public String toString() {
		return "EquipmentHistory [hno=" + hno + ", historyDate=" + historyDate + ", stickerNo=" + stickerNo
				+ ", actionType=" + actionType + "]";
	}
}