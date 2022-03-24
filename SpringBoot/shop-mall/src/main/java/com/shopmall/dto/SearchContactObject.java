package com.shopmall.dto;

public class SearchContactObject {

	private String statusContact;
	private String tuNgay;
	private String denNgay;
	
	public SearchContactObject() {
		statusContact = "";
		tuNgay = "";
		denNgay = "";
	}

	public String getStatusContact() {
		return statusContact;
	}

	public void setStatusContact(String statusContact) {
		this.statusContact = statusContact;
	}

	public String getTuNgay() {
		return tuNgay;
	}

	public void setTuNgay(String tuNgay) {
		this.tuNgay = tuNgay;
	}

	public String getDenNgay() {
		return denNgay;
	}

	public void setDenNgay(String denNgay) {
		this.denNgay = denNgay;
	}

}
