package com.shopmall.service;

import java.text.ParseException;

import org.springframework.data.domain.Page;

import com.shopmall.dto.SearchContactObject;
import com.shopmall.entities.Contact;

public interface ContactService {

	Page<Contact> getContactByFilter(SearchContactObject object, int page) throws ParseException;

	Contact findById(long id);
	
	Contact save(Contact lh);
	
	int countByStatus(String status);

}
