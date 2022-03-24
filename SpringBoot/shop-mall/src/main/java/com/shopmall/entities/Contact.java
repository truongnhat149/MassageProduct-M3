package com.shopmall.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name=Contact.CONTACT)
public class Contact {

	/**
	 *
	 */
	static final String CONTACT = "contact";

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String emailContact;

	private String contentContact;

	private String noiDungTraLoi;

	@DateTimeFormat(pattern="dd/MM/yyyy HH:mm")
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm", timezone="GMT+7")
	private Date ngayContact;

	@DateTimeFormat(pattern="dd/MM/yyyy HH:mm")
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm", timezone="GMT+7")
	private Date ngayTraLoi;

	private String trangThai;
	
	@ManyToOne
	@JoinColumn(name = "respondent_code")
	private User nguoiTraLoi;

	public User getNguoiTraLoi() {
		return nguoiTraLoi;
	}

	public void setNguoiTraLoi(User nguoiTraLoi) {
		this.nguoiTraLoi = nguoiTraLoi;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmailContact() {
		return emailContact;
	}

	public void setEmailContact(String emailContact) {
		this.emailContact = emailContact;
	}

	public String getContentContact() {
		return contentContact;
	}

	public void setContentContact(String contentContact) {
		this.contentContact = contentContact;
	}

	public String getNoiDungTraLoi() {
		return noiDungTraLoi;
	}

	public void setNoiDungTraLoi(String noiDungTraLoi) {
		this.noiDungTraLoi = noiDungTraLoi;
	}

	public Date getNgayContact() {
		return ngayContact;
	}

	public void setNgayContact(Date ngayContact) {
		this.ngayContact = ngayContact;
	}

	public Date getNgayTraLoi() {
		return ngayTraLoi;
	}

	public void setNgayTraLoi(Date ngayTraLoi) {
		this.ngayTraLoi = ngayTraLoi;
	}

	public String getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(String trangThai) {
		this.trangThai = trangThai;
	}
}
