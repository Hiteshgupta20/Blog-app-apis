package com.blog.blog.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.blog.blog.payloads.ApiResponse;
import com.blog.blog.payloads.UserDto;

public interface UserService {

	
	UserDto createUser(UserDto user);
	UserDto updateUser(UserDto user, Integer userId);
	UserDto getUserById(Integer userID);
	List<UserDto> getAllUser();
	void deleteUser(Integer userId);
}
