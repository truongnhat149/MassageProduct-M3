package com.shopmall.config;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.shopmall.entities.User;
import com.shopmall.entities.Role;
import com.shopmall.repository.UserRepository;
import com.shopmall.repository.RoleRepository;

@Component
public class DataSeeder implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		// vai trof
		if (roleRepository.findByNameRole("ROLE_ADMIN") == null) {
			roleRepository.save(new Role("ROLE_ADMIN"));
		}

		if (roleRepository.findByNameRole("ROLE_MEMBER") == null) {
			roleRepository.save(new Role("ROLE_MEMBER"));
		}
		
		if (roleRepository.findByNameRole("ROLE_SHIPPER") == null) {
			roleRepository.save(new Role("ROLE_SHIPPER"));
		}

		// Admin account
		if (userRepository.findByEmail("admin@gmail.com") == null) {
			User admin = new User();
			admin.setEmail("admin@gmail.com");
			admin.setPassword(passwordEncoder.encode("123456"));
			admin.setFullname("LPNT");
			admin.setphone("123456789");
			HashSet<Role> roles = new HashSet<>();
			roles.add(roleRepository.findByNameRole("ROLE_ADMIN"));
			roles.add(roleRepository.findByNameRole("ROLE_MEMBER"));
			admin.setRole(roles);
			userRepository.save(admin);
		}

		// Member account
		if (userRepository.findByEmail("member@gmail.com") == null) {
			User member = new User();
			member.setEmail("member@gmail.com");
			member.setPassword(passwordEncoder.encode("123456"));
			HashSet<Role> roles = new HashSet<>();
			roles.add(roleRepository.findByNameRole("ROLE_MEMBER"));
			member.setRole(roles);
			userRepository.save(member);
		}
		
		// Shipper account
		if (userRepository.findByEmail("shipper@gmail.com") == null) {
			User member = new User();
			member.setEmail("shipper@gmail.com");
			member.setPassword(passwordEncoder.encode("123456"));
			HashSet<Role> roles = new HashSet<>();
			roles.add(roleRepository.findByNameRole("ROLE_SHIPPER"));
			member.setRole(roles);
			userRepository.save(member);
		}
	}
}
