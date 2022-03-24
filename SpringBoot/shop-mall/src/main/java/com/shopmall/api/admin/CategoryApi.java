package com.shopmall.api.admin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
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

import com.shopmall.entities.Category;
import com.shopmall.entities.ResponseObject;
import com.shopmall.service.CategoryService;

@RestController
@RequestMapping("api/category")

public class CategoryApi {

	@Autowired
	private CategoryService CategoryService;

	@GetMapping("/all")
	public Page<Category> getAllCategory(@RequestParam(defaultValue = "1") int page) {
		return CategoryService.getAllCategoryForPageable(page-1,6);
	}
	
	@GetMapping("/allForReal")
	public List<Category> getRealAllCategory() {
		return CategoryService.getAllCategory();
	}

	@GetMapping("/{id}")
	public Category getCategoryById(@PathVariable long id) {
		return CategoryService.getCategoryById(id);
	}

	@PostMapping(value = "/save")
	public ResponseObject addCategory(@RequestBody @Valid Category newCategory, BindingResult result, HttpServletRequest request) {
		
		ResponseObject ro = new ResponseObject();
		
		if (result.hasErrors()) {

			Map<String, String> errors = result.getFieldErrors().stream()
					.collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
			ro.setErrorMessages(errors);
			
			List<String> keys = new ArrayList<String>(errors.keySet());			
			for (String key: keys) {
			    System.out.println(key + ": " + errors.get(key));
			}
			
			ro.setStatus("fail");
			errors = null;
			;
		} else {
			CategoryService.save(newCategory);
			ro.setData(newCategory);
			ro.setStatus("success");
		}
		return ro;
	}
	
	@PutMapping(value = "/update")
	public ResponseObject updateCategory(@RequestBody @Valid Category editCategory, BindingResult result, HttpServletRequest request) {
		
		ResponseObject ro = new ResponseObject();		
		if (result.hasErrors()) {

			Map<String, String> errors = result.getFieldErrors().stream()
					.collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
			ro.setErrorMessages(errors);
			ro.setStatus("fail");
			errors = null;
			
		} else {
			CategoryService.update(editCategory);
			ro.setData(editCategory);
			ro.setStatus("success");
		}
		
		return ro;
	}

	@DeleteMapping("/delete/{id}")
	public String deleteCategory(@PathVariable long id, HttpServletRequest request) {
		CategoryService.deleteById(id);
		request.getSession().setAttribute("listCategory", CategoryService.getAllCategory());;
		return "OK !";
	}

}
