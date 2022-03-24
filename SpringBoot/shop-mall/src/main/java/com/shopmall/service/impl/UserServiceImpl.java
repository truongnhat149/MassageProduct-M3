package com.shopmall.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.shopmall.dto.AccountDTO;
import com.shopmall.entities.User;
import com.shopmall.entities.Role;
import com.shopmall.repository.UserRepository;
import com.shopmall.repository.RoleRepository;
import com.shopmall.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private RoleRepository roleRepo;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public User findByEmail(String email) {
		return userRepo.findByEmail(email);
	}

	@Override
	public User findByConfirmationToken(String confirmationToken) {
		return null;
	}

	@Override
	public User saveUserForMember(User nd) {
		nd.setPassword(bCryptPasswordEncoder.encode(nd.getPassword()));
		Set<Role> setRole = new HashSet<>();
		setRole.add(roleRepo.findByNameRole("ROLE_MEMBER"));
		nd.setRole(setRole);
		return userRepo.save(nd);
	}

	@Override
	public User findById(long id) {
		User nd = userRepo.findById(id).get();
		return nd;
	}

	@Override
	public User updateUser(User nd) {
		return userRepo.save(nd);
	}

	@Override
	public void changePass(User nd, String newPass) {
		nd.setPassword(bCryptPasswordEncoder.encode(newPass));
		userRepo.save(nd);
	}

	@Override
	public Page<User> getUserByRole(Set<Role> Role,  int page) {
		return userRepo.findByRole(Role, PageRequest.of(page - 1, 6));
	}

	@Override
	public List<User> getUserByRole(Set<Role> Role) {
		return userRepo.findByRole(Role);
	}

	@Override
	public User saveUserForAdmin(AccountDTO dto) {
		User nd = new User();
		nd.setFullname(dto.getFullname());
		nd.setAddress(dto.getAddress());
		nd.setEmail(dto.getEmail());
		nd.setphone(dto.getSdt());
		nd.setPassword(bCryptPasswordEncoder.encode(dto.getPassword()));
		
		Set<Role> role  = new HashSet<>();
		role.add(roleRepo.findByNameRole(dto.getNameRole()));
		nd.setRole(role);
		
		return userRepo.save(nd);
	}

	@Override
	public void deleteById(long id) {
		userRepo.deleteById(id);
	}

}
