package com.blog.blog.controllers;

import com.blog.blog.payloads.ApiResponse;
import com.blog.blog.payloads.UserDto;
import com.blog.blog.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    //Post - create user
    @PostMapping("/createUser")
    public ResponseEntity<ApiResponse> createUser(@Valid @RequestBody UserDto userDto) {
        return this.userService.createUser(userDto);

    }

    //Put - update user
    @PutMapping("/updateUser/{userId}")
    public ResponseEntity<ApiResponse> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable Integer userId) {
        return userService.updateUser(userDto, userId);
    }


    //get - get UserEntity
    @GetMapping("/")
    public ResponseEntity<ApiResponse> getAllUser() {
        return userService.getAllUser();
    }

    //delete - delete user

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<ApiResponse> deleteUser(@Valid @PathVariable Integer userId) {
        return userService.deleteUser(userId);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<ApiResponse> getUserById(@PathVariable Integer userId) {
        return userService.getUserById(userId);
    }


}
