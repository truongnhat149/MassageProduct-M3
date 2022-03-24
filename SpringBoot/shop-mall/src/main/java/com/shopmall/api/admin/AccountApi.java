package com.shopmall.api.admin;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shopmall.dto.AccountDTO;
import com.shopmall.entities.User;
import com.shopmall.entities.ResponseObject;
import com.shopmall.entities.Role;
import com.shopmall.service.UserService;
import com.shopmall.service.RoleService;
import com.shopmall.validator.UserValidator;

@RestController
@RequestMapping("/api/account")
public class AccountApi {

	@Autowired
	private UserService UserService;

	@Autowired
	private RoleService RoleService;

	@GetMapping("/all")
	public Page<User> getUserByRole(@RequestParam("nameRole") String nameRole,
			@RequestParam(defaultValue = "1") int page) {
		Set<Role> Role = new HashSet<>();
		Role.add(RoleService.findByNameRole(nameRole));

		return UserService.getUserByRole(Role, page);
	}

	@PostMapping("/save")
	public ResponseObject saveAccount(@RequestBody @Valid AccountDTO dto, BindingResult result, Model model) {
		
		ResponseObject ro = new ResponseObject();

		if(UserService.findByEmail(dto.getEmail()) != null) {
			result.rejectValue("email", "error.email","Email đã được đăng ký");
		}
		if(!dto.getConfirmPassword().equals(dto.getPassword())) {
			result.rejectValue("confirmPassword", "error.confirmPassword","Nhắc lại mật khẩu không đúng");
		}

		if (result.hasErrors()) {
			setErrorsForResponseObject(result, ro);
		} else {
			ro.setStatus("success");
			UserService.saveUserForAdmin(dto);
		}	
		return ro;
	}

	@DeleteMapping("/delete/{id}")
	public void deleteAccount(@PathVariable long id) {
		UserService.deleteById(id);
	}
	public void setErrorsForResponseObject(BindingResult result, ResponseObject object) {

		Map<String, String> errors = result.getFieldErrors().stream()
				.collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
		object.setErrorMessages(errors);
		object.setStatus("fail");
		
		List<String> keys = new ArrayList<String>(errors.keySet());			
		for (String key: keys) {
		    System.out.println(key + ": " + errors.get(key));
		}
		
		errors = null;
	}
}
