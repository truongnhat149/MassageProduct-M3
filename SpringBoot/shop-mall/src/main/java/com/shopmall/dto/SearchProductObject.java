package com.shopmall.dto;

public class SearchProductObject {
	private String categoryId;
	private String hangSXId;
	private String unitPrice;
	
	// sap xep theo gia
	private String sapXepTheoGia;
	private String[] keyword;
	private String sort;
	
	// sap xep theo danhmuc va hangsx
	private String brand;
	private String manufactor;
	


	public SearchProductObject() {
		categoryId = "";
		hangSXId = "";
		unitPrice = "";
		sapXepTheoGia = "ASC";
	}


	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getHangSXId() {
		return hangSXId;
	}

	public void setHangSXId(String hangSXId) {
		this.hangSXId = hangSXId;
	}

	public String getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(String unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String[] getKeyword() {
		return keyword;
	}

	public void setKeyword(String[] keyword) {
		this.keyword = keyword;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getSapXepTheoGia() {
		return sapXepTheoGia;
	}

	public void setSapXepTheoGia(String sapXepTheoGia) {
		this.sapXepTheoGia = sapXepTheoGia;
	}
	
	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}
	
	public String getManufactor() {
		return manufactor;
	}

	public void setManufactor(String manufactor) {
		this.manufactor = manufactor;
	}

	@Override
	public String toString() {
		return "SearchObject [categoryId=" + categoryId + ", hangSXId=" + hangSXId + ", price=" + unitPrice + "]";
	}
}
