package com.shopmall.dto;

public class ListWorkDTO {

	private int soOrderMoi; // số đơn hàng mới
	private int soContactMoi; // số liên hệ mới;
	private int soOrderChoDuyet; // số đơn hàng chờ duyệt
	
	public int getsoOrderMoi() {
		return soOrderMoi;
	}
	public void setsoOrderMoi(int soOrderMoi) {
		this.soOrderMoi = soOrderMoi;
	}
	
	public int getSoContactMoi() {
		return soContactMoi;
	}
	public void setSoContactMoi(int soContactMoi) {
		this.soContactMoi = soContactMoi;
	}
	public int getsoOrderChoDuyet() {
		return soOrderChoDuyet;
	}
	public void setsoOrderChoDuyet(int soOrderChoDuyet) {
		this.soOrderChoDuyet = soOrderChoDuyet;
	}
}
