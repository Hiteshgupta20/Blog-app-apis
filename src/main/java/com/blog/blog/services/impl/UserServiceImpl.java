package com.blog.blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.blog.exceptions.*;
import com.blog.blog.entities.User;
import com.blog.blog.payloads.UserDto;
import com.blog.blog.repositories.UserRepo;
import com.blog.blog.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public UserDto createUser(UserDto userDto) {
		User user = this.dtoToUser(userDto);
		User savedUser = userRepo.save(user);
		System.out.println(savedUser);
		return userToDto(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
	
		User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User","id",userId));
		user.setAbout(userDto.getAbout());
		user.setEmail(userDto.getEmail());
		user.setName(userDto.getName());
		user.setPassword(userDto.getPassword());
		User updateUser = this.userRepo.save(user);
		UserDto userDto1 = this.userToDto(updateUser);
		return userDto1;
	}

	@Override
	public UserDto getUserById(Integer userId) {
		User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User","id",userId));
		return this.userToDto(user);
	}

	@Override
	public List<UserDto> getAllUser() {
	List<User> users = userRepo.findAll();
	List<UserDto> userDtos = users.stream().map(user-> userToDto(user)).collect(Collectors.toList());
		return userDtos;
	}

	@Override
	public void deleteUser(Integer userId) {
		User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User","id",userId));
		userRepo.delete(user);

	}
	
	private User dtoToUser(UserDto userDto) {
		User user = modelMapper.map(userDto, User.class);
//		user.setAbout(userDto.getAbout());
//		user.setEmail(userDto.getEmail());
//		user.setId(userDto.getId());
//		user.setName(userDto.getName());
//		user.setPassword(userDto.getPassword());
		return user;
	}
	
	private UserDto userToDto(User user) {
		UserDto userDto = modelMapper.map(user, UserDto.class);
//		userDto.setAbout(user.getAbout());
//		userDto.setEmail(user.getEmail());
//		userDto.setId(user.getId());
//		userDto.setName(user.getName());
//		userDto.setPassword(user.getPassword());
		return userDto;
	}
	

}
