package com.shopmall.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.shopmall.dto.ProductDto;
import com.shopmall.service.CategoryService;

@Component
public class ProductDtoValidator implements Validator{
	
	@Autowired
	private CategoryService dmService;

	@Override
	public boolean supports(Class<?> clazz) {
		return ProductDto.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		ProductDto s = (ProductDto) target;
		
		ValidationUtils.rejectIfEmpty(errors, "nameProduct", "error.nameProduct", "Tên sản phẩm không được trống");
		ValidationUtils.rejectIfEmpty(errors, "unitPrice", "error.unitPrice", "Đơn giá không được trống");
		ValidationUtils.rejectIfEmpty(errors, "donViKho", "error.donViKho", "Đơn vị kho không được trống");
		ValidationUtils.rejectIfEmpty(errors, "thongTinBaoHanh", "error.thongTinBaoHanh", "Thông tin bảo hành không được trống");
		ValidationUtils.rejectIfEmpty(errors, "genaralInfomation", "error.genaralInfomation", "Thông tin chung không được trống");
		
		if(Integer.parseInt(s.getUnitPrice()) < 0) {
			errors.rejectValue("unitPrice", "error.unitPrice", "Đơn giá không được âm");
		}
		
		if(Integer.parseInt(s.getDonViKho()) < 0) {
			errors.rejectValue("donViKho", "error.donViKho", "Đơn giá không được âm");
		}
		String nameCategory = dmService.getCategoryById(s.getCategoryId()).getNameCategory().toLowerCase();
		
		if(nameCategory.contains("Massage".toLowerCase())) {
			ValidationUtils.rejectIfEmpty(errors, "thietKe", "error.thietKe", "Thiết kế không được trống");
		}
		
	}

}
