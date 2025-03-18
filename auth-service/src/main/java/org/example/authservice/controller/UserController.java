package org.example.authservice.controller;

import jakarta.validation.Valid;
import org.example.authservice.dto.request.UserCreateRequest;
import org.example.authservice.dto.request.UserUpdateRequest;
import org.example.authservice.dto.response.UserResponse;
import org.example.authservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    UserResponse createUser(@RequestBody @Valid UserCreateRequest userCreateRequest) {
       return userService.createUser(userCreateRequest);

    }

    @GetMapping
    List<UserResponse> findAllUsers() {
        return userService.findAllUser();
    }
    @GetMapping("/{userId}")
    UserResponse findUserById(@PathVariable String userId) {
        return userService.findUserById(userId);
    }

    @PutMapping("/{userId}")
    UserResponse updateUser(@PathVariable String userId, @RequestBody UserUpdateRequest userUpdateRequest) {
        return userService.updateUser(userUpdateRequest, userId);
    }

}
