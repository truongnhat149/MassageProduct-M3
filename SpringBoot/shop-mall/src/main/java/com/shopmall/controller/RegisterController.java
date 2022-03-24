package com.shopmall.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.shopmall.entities.User;
import com.shopmall.service.UserService;
import com.shopmall.service.SecurityService;
import com.shopmall.validator.UserValidator;

@Controller
public class RegisterController {

    @Autowired
    private UserService UserService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator UserValidator;
	
	
	@GetMapping("/register")
	public String registerPage(Model model) {
		model.addAttribute("newUser", new User());
		return "client/register";
	}
	
	@PostMapping("/register")
	public String registerProcess(@ModelAttribute("newUser") @Valid User User, BindingResult bindingResult, Model model) {
	    
		UserValidator.validate(User, bindingResult);
		
        if (bindingResult.hasErrors()) {
            return "client/register";
        }
        
        UserService.saveUserForMember(User);

        securityService.autologin(User.getEmail(), User.getConfirmPassword());

        return "redirect:/";
	}
}
