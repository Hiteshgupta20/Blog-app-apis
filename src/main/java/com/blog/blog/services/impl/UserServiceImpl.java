package com.blog.blog.services.impl;

import com.blog.blog.entities.UserEntity;
import com.blog.blog.exceptions.ResourceNotFoundException;
import com.blog.blog.payloads.ApiResponse;
import com.blog.blog.payloads.UserDto;
import com.blog.blog.repositories.UserRepo;
import com.blog.blog.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ResponseEntity createUser(UserDto userDto) {
        UserEntity userEntity = modelMapper.map(userDto, UserEntity.class);
        UserEntity savedUserEntity = userRepo.save(userEntity);
        return ApiResponse.success(200, "User Saved Successfully", savedUserEntity);
    }

    @Override
    public ResponseEntity updateUser(UserDto userDto, Integer userId) {

        UserEntity userEntity = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("UserEntity", "id", userId));
        userEntity.setAbout(userDto.getAbout());
        userEntity.setEmail(userDto.getEmail());
        userEntity.setName(userDto.getName());
        userEntity.setPassword(userDto.getPassword());
        UserEntity updateUserEntity = this.userRepo.save(userEntity);
//        UserDto userDto1 = this.userToDto(updateUserEntity);
        return ApiResponse.success(200, "User Updated Successfully", modelMapper.map(updateUserEntity, UserDto.class));
    }

    @Override
    public ResponseEntity getUserById(Integer userId) {
        UserEntity userEntity = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("UserEntity", "id", userId));
        return ApiResponse.success(200, "User fetched Successfully", userEntity);
    }

    @Override
    public ResponseEntity getAllUser() {
        List<UserEntity> userEntities = userRepo.findAll();
        List<UserDto> userDtos = userEntities.stream().map(userEntity -> modelMapper.map(userEntity, UserDto.class)).collect(Collectors.toList());
        return ApiResponse.success(200, "Users Fetched Successfully", userDtos);
    }

    @Override
    public ResponseEntity deleteUser(Integer userId) {
        UserEntity userEntity = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("UserEntity", "id", userId));
        userRepo.delete(userEntity);
        return ApiResponse.success(200, "User deleted successfully", null);

    }


}
