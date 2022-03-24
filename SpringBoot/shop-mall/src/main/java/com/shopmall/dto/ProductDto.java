package com.shopmall.dto;


import org.springframework.web.multipart.MultipartFile;

public class ProductDto {

	private String id;

	private String nameProduct;
	private String unitPrice;
	private String donViKho;
	private String thongTinBaoHanh;
	private String genaralInfomation;
	private String thietKe;

	private long CategoryId;
	private long nhaSXId;
	
	private MultipartFile hinhAnh;

	public MultipartFile getHinhAnh() {
		return hinhAnh;
	}

	public void setHinhAnh(MultipartFile hinhAnh) {
		this.hinhAnh = hinhAnh;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNameProduct() {
		return nameProduct;
	}

	public void setNameProduct(String nameProduct) {
		this.nameProduct = nameProduct;
	}

	public String getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(String unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getDonViKho() {
		return donViKho;
	}

	public void setDonViKho(String donViKho) {
		this.donViKho = donViKho;
	}

	public String getThongTinBaoHanh() {
		return thongTinBaoHanh;
	}

	public void setThongTinBaoHanh(String thongTinBaoHanh) {
		this.thongTinBaoHanh = thongTinBaoHanh;
	}

	public String getGenaralInfomation() {
		return genaralInfomation;
	}

	public void setGenaralInfomation(String genaralInfomation) {
		this.genaralInfomation = genaralInfomation;
	}


	public String getThietKe() {
		return thietKe;
	}

	public void setThietKe(String thietKe) {
		this.thietKe = thietKe;
	}

	public long getCategoryId() {
		return CategoryId;
	}

	public void setCategoryId(long CategoryId) {
		this.CategoryId = CategoryId;
	}

	public long getNhaSXId() {
		return nhaSXId;
	}

	public void setNhaSXId(long nhaSXId) {
		this.nhaSXId = nhaSXId;
	}
	


	@Override
	public String toString() {
		return "ProductDto [id=" + id + ", nameProduct=" + nameProduct + ", unitPrice=" + unitPrice + ", donViKho=" + donViKho
				+ ", thongTinBaoHanh=" + thongTinBaoHanh + ", genaralInfomation=" + genaralInfomation 
				+ ", thietKe=" + thietKe
				+ ", CategoryId=" + CategoryId + ", nhaSXId=" + nhaSXId + ", hinhAnh="
				+ hinhAnh + "]";
	}
}
