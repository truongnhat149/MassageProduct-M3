package com.shopmall.service;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;

import com.shopmall.dto.AccountDTO;
import com.shopmall.entities.User;
import com.shopmall.entities.Role;

public interface UserService {

	User findByEmail(String email);

	User findByConfirmationToken(String confirmationToken);

	User saveUserForMember(User nd);

	User findById(long id);

	User updateUser(User nd);

	void changePass(User nd, String newPass);

	Page<User> getUserByRole(Set<Role> Role, int page);

	List<User> getUserByRole(Set<Role> Role);
	
	User saveUserForAdmin(AccountDTO dto);

	void deleteById(long id);

}
