package com.shopmall.api.admin;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shopmall.dto.ContactDTO;
import com.shopmall.dto.SearchContactObject;
import com.shopmall.entities.Contact;
import com.shopmall.entities.ResponseObject;
import com.shopmall.service.ContactService;
import com.shopmall.service.UserService;
import com.shopmall.ulti.EmailUlti;

@RestController
@RequestMapping("/api/contact")
public class ContactApi {

	@Autowired
	private ContactService contactService;

	@Autowired
	private UserService userService;

	@Autowired
	private EmailUlti emailUlti;

	@GetMapping("/all")
	public Page<Contact> getContactByFilter(@RequestParam(defaultValue = "1") int page,
			@RequestParam String trangThaiContact, @RequestParam String tuNgay, @RequestParam String denNgay)
			throws ParseException {

		SearchContactObject object = new SearchContactObject();
		object.setDenNgay(denNgay);
		object.setStatusContact(trangThaiContact);
		object.setTuNgay(tuNgay);

		Page<Contact> listContact = contactService.getContactByFilter(object, page);
		return listContact;
	}

	@GetMapping("/{id}")
	public Contact getContactById(@PathVariable long id) {
		return contactService.findById(id);
	}

	@PostMapping("/reply")
	public ResponseObject tradLoiContactProcess(@RequestBody @Valid ContactDTO dto, BindingResult result) {

		ResponseObject ro = new ResponseObject();

		if (result.hasErrors()) {

			Map<String, String> errors = result.getFieldErrors().stream()
					.collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
			ro.setErrorMessages(errors);

//			List<String> keys = new ArrayList<String>(errors.keySet());
//			for (String key : keys) {
//				System.out.println(key + ": " + errors.get(key));
//			}

			ro.setStatus("fail");
		} else {
			System.out.println(dto.toString());
			
			emailUlti.sendEmail(dto.getDiaChiDen(), dto.getTieuDe(), dto.getNoiDungTraLoi());
			
			Contact Contact = contactService.findById(dto.getId());
			Contact.setTrangThai("Đã trả lời");
			Contact.setNgayTraLoi(new Date());
			Contact.setNoiDungTraLoi(dto.getNoiDungTraLoi());

			contactService.save(Contact);
			ro.setStatus("success");
		}
		return ro;

	}
}
