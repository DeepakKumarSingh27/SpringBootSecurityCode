package com.becoder.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.condition.RequestConditionHolder;

import com.becoder.entity.User;
import com.becoder.repository.UserRepo;

import jakarta.servlet.http.HttpSession;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	

	@Override
	public User saveUser(User user) {
	String password =  passwordEncoder.encode(user.getPassword());
	user.setPassword(password);
		User newUser = userRepo.save(user);
		return newUser;
	}

	@Override
	public void removeSessionMessage() {
       HttpSession session = ((ServletRequestAttributes)(RequestContextHolder.getRequestAttributes()))
       .getRequest().getSession();
       
       session.removeAttribute("msg");
	}

}
