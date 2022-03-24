package com.shopmall.dto;


public class SearchOrderObject {

	private String statusDon;
	private String tuNgay;
	private String denNgay;

	public SearchOrderObject() {
		statusDon = "";
		tuNgay = "";
		denNgay = "";
	}

	public String getStatusDon() {
		return statusDon;
	}

	public void setStatusDon(String statusDon) {
		this.statusDon = statusDon;
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

	@Override
	public String toString() {
		return "SearchOrderObject [statusDon=" + statusDon + ", tuNgay=" + tuNgay + ", denNgay=" + denNgay
				+ "]";
	}
	
	
}
