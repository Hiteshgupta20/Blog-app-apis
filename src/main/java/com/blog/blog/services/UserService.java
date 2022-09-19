package com.blog.blog.services;

import com.blog.blog.payloads.UserDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {

	
	ResponseEntity createUser(UserDto user);
	ResponseEntity updateUser(UserDto user, Integer userId);
	ResponseEntity getUserById(Integer userID);
	ResponseEntity getAllUser();
	ResponseEntity deleteUser(Integer userId);
}
