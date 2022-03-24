package com.shopmall.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.shopmall.dto.ContactDTO;
import com.shopmall.dto.SearchContactObject;
import com.shopmall.entities.Contact;
import com.shopmall.entities.QContact;
import com.shopmall.repository.ContactRepository;
import com.shopmall.service.ContactService;
import com.querydsl.core.BooleanBuilder;

@Service
public class ContactServiceImpl implements ContactService {

	@Autowired
	private ContactRepository contactRepo;

	@Override
	public Page<Contact> getContactByFilter(SearchContactObject object, int page) throws ParseException {
		BooleanBuilder builder = new BooleanBuilder();

		String trangThai = object.getStatusContact();
		String tuNgay = object.getTuNgay();
		String denNgay = object.getDenNgay();
		SimpleDateFormat formatDate = new SimpleDateFormat("dd-MM-yyyy");

		if (!trangThai.equals("")) {
			builder.and(QContact.contact.trangThai.eq(trangThai));
		}

		if (!tuNgay.equals("") && tuNgay != null) {
			builder.and(QContact.contact.ngayContact.goe(formatDate.parse(tuNgay)));
		}

		if (!denNgay.equals("") && denNgay != null) {
			builder.and(QContact.contact.ngayContact.loe(formatDate.parse(denNgay)));
		}

		return contactRepo.findAll(builder, PageRequest.of(page - 1, 2));
	}

	@Override
	public Contact findById(long id) {
		return contactRepo.findById(id).get();
	}

	@Override
	public Contact save(Contact lh) {
		return contactRepo.save(lh);
	}

	@Override
	public int countByStatus(String status) {
		return contactRepo.countByTrangThai(status);
	}

}
