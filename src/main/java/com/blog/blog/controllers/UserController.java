package com.blog.blog.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.blog.payloads.ApiResponse;
import com.blog.blog.payloads.UserDto;
import com.blog.blog.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	//Post - create user
	@PostMapping("/createUser")
	public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
		UserDto createdUserDto = this.userService.createUser(userDto);
		return new ResponseEntity<>(createdUserDto,HttpStatus.CREATED);
		
	}
	//Put - update user
	@PutMapping("/updateUser/{userId}")
	public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto, @PathVariable Integer userId){
		UserDto  updatedUserDto = userService.updateUser(userDto, userId);
		return new ResponseEntity<UserDto>(updatedUserDto,HttpStatus.OK);
	}
	
	
	//get - get User
	@GetMapping("/")
	public ResponseEntity<List<UserDto>> getAllUser(){
		List<UserDto> getAllUsersDto = userService.getAllUser();
		return ResponseEntity.ok(getAllUsersDto);
	}
	
	//delete - delete user
	
	@DeleteMapping("/delete/{userId}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable Integer userId) {
		userService.deleteUser(userId);
		return new ResponseEntity(new ApiResponse(200, "User Deleted Succesfully", null),HttpStatus.OK);
	}
	

}
