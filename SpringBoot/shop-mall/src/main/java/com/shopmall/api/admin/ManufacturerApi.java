package com.shopmall.api.admin;

import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shopmall.entities.Manufacturer;
import com.shopmall.entities.ResponseObject;
import com.shopmall.service.ManufacturerService;

@RestController
@RequestMapping("/api/brand")
public class ManufacturerApi {

	@Autowired
	private ManufacturerService service;

	@GetMapping("/all")
	public Page<Manufacturer> getAllManufacturer(@RequestParam(defaultValue = "1") int page) {
		return service.getALlHangSX(page-1,6);
	}

	@GetMapping("/{id}")
	public Manufacturer getManufacturerById(@PathVariable long id) {
		return service.getHSXById(id);
	}

	@PostMapping(value = "/save")
	public ResponseObject addManufacturer(@RequestBody @Valid Manufacturer newManufacturer, BindingResult result) {

		ResponseObject ro = new ResponseObject();

		if (result.hasErrors()) {
			Map<String, String> errors = result.getFieldErrors().stream()
					.collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
			ro.setErrorMessages(errors);
			ro.setStatus("fail");
		} else {
			service.save(newManufacturer);
			ro.setData(newManufacturer);
			ro.setStatus("success");
		}
		return ro;
	}

	@PutMapping(value = "/update")
	public ResponseObject updateManufacturer(@RequestBody @Valid Manufacturer editManufacturer, BindingResult result) {

		ResponseObject ro = new ResponseObject();
		if (result.hasErrors()) {
			Map<String, String> errors = result.getFieldErrors().stream()
					.collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
			ro.setErrorMessages(errors);
			ro.setStatus("fail");
			errors = null;
		} else {
			service.update(editManufacturer);
			ro.setData(editManufacturer);
			ro.setStatus("success");
		}

		return ro;
	}

	@DeleteMapping("/delete/{id}")
	public String deleteManufacturer(@PathVariable long id) {
		service.deleteById(id);
		return "OK !";
	}
}
