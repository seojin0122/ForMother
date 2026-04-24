package com.seojin.equipment.dto;

import java.io.Serializable;

public class Equipment implements Serializable {
	private static final long serialVersionUID = 1L;

	private int eno;
	private String stickerNo;
	private String productName;
	private String modelYear;
	private String manager;
	private String currentLocation;
	private String status;
	private String note;

	public Equipment() {
	}

	public Equipment(int eno, String stickerNo, String productName, String modelYear, String manager,
			String currentLocation, String status, String note) {
		this.eno = eno;
		this.stickerNo = stickerNo;
		this.productName = productName;
		this.modelYear = modelYear;
		this.manager = manager;
		this.currentLocation = currentLocation;
		this.status = status;
		this.note = note;
	}

	public int getEno() {
		return eno;
	}

	public void setEno(int eno) {
		this.eno = eno;
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

	public String getModelYear() {
		return modelYear;
	}

	public void setModelYear(String modelYear) {
		this.modelYear = modelYear;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public String getCurrentLocation() {
		return currentLocation;
	}

	public void setCurrentLocation(String currentLocation) {
		this.currentLocation = currentLocation;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@Override
	public String toString() {
		return "Equipment [eno=" + eno + ", stickerNo=" + stickerNo + ", productName=" + productName
				+ ", currentLocation=" + currentLocation + ", status=" + status + "]";
	}
}