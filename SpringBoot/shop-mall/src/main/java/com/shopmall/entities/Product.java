package com.shopmall.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name=Product.PRODUCT)
public class Product {
	
	/**
	 *
	 */
	static final String PRODUCT = "product";

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String nameProduct;
	private long unitPrice;
	private int donViKho;
	private int sellingUnit;
	private String thongTinBaoHanh;
	private String genaralInfomation;
	private String thietKe;
	
	@Transient
	@JsonIgnore
	private MultipartFile hinhAnh;

	@ManyToOne
	@JoinColumn(name = "category_code")
	private Category category;

	@ManyToOne
	@JoinColumn(name = "manufacturer_code")
	private Manufacturer manufacturer;

	public String getNameProduct() {
		return nameProduct;
	}

	public void setNameProduct(String nameProduct) {
		this.nameProduct = nameProduct;
	}

	public long getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(long unitPrice) {
		this.unitPrice = unitPrice;
	}

	public int getDonViKho() {
		return donViKho;
	}

	public void setDonViKho(int donViKho) {
		this.donViKho = donViKho;
	}

	public int getsellingUnit() {
		return sellingUnit;
	}

	public void setsellingUnit(int sellingUnit) {
		this.sellingUnit = sellingUnit;
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

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}


	public String getThietKe() {
		return thietKe;
	}

	public void setThietKe(String thietKe) {
		this.thietKe = thietKe;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Manufacturer getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(Manufacturer manufacturer) {
		this.manufacturer = manufacturer;
	}
	

	public MultipartFile getHinhAnh() {
		return hinhAnh;
	}

	public void setHinhAnh(MultipartFile hinhAnh) {
		this.hinhAnh = hinhAnh;
	}

}
