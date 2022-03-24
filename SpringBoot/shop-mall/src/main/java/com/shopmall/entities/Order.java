package com.shopmall.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="order")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@OneToMany(mappedBy = "order")
	private List<DetailsOrder> listDetails;

	private String diaChiNhan;
	private String sdtNhanHang;
	private String hoTenNguoiNhan;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+7")
	private Date orderDate;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+7")
	private Date ngayGiaoHang;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+7")
	private Date ngayNhanHang;

	private String statusOrder;
	private String ghiChu;

	@ManyToOne(optional = true)
	@JoinColumn(name = "orderer_code")
	private User nguoiDat;

	@ManyToOne(optional = true)
	@JoinColumn(name = "shipper_code")
	private User shipper;

	private long tongGiaTri;

	public long getTongGiaTri() {
		return tongGiaTri;
	}

	public void setTongGiaTri(long tongGiaTri) {
		this.tongGiaTri = tongGiaTri;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<DetailsOrder> getListDetails() {
		return listDetails;
	}

	public void setListDetails(List<DetailsOrder> listDetails) {
		this.listDetails = listDetails;
	}

	public String getDiaChiNhan() {
		return diaChiNhan;
	}

	public void setDiaChiNhan(String diaChiNhan) {
		this.diaChiNhan = diaChiNhan;
	}

	public String getSdtNhanHang() {
		return sdtNhanHang;
	}

	public void setSdtNhanHang(String sdtNhanHang) {
		this.sdtNhanHang = sdtNhanHang;
	}

	public Date getorderDate() {
		return orderDate;
	}

	public void setorderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Date getNgayGiaoHang() {
		return ngayGiaoHang;
	}

	public void setNgayGiaoHang(Date ngayGiaoHang) {
		this.ngayGiaoHang = ngayGiaoHang;
	}

	public Date getNgayNhanHang() {
		return ngayNhanHang;
	}

	public void setNgayNhanHang(Date ngayNhanHang) {
		this.ngayNhanHang = ngayNhanHang;
	}

	public User getNguoiDat() {
		return nguoiDat;
	}

	public void setNguoiDat(User nguoiDat) {
		this.nguoiDat = nguoiDat;
	}

	public User getShipper() {
		return shipper;
	}

	public void setShipper(User shipper) {
		this.shipper = shipper;
	}

	public String getStatusOrder() {
		return statusOrder;
	}

	public void setStatusOrder(String statusOrder) {
		this.statusOrder = statusOrder;
	}

	public String getGhiChu() {
		return ghiChu;
	}

	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}

	public String getHoTenNguoiNhan() {
		return hoTenNguoiNhan;
	}

	public void setHoTenNguoiNhan(String hoTenNguoiNhan) {
		this.hoTenNguoiNhan = hoTenNguoiNhan;
	}

	public Order() {
		// TODO Auto-generated constructor stub
	}

}
