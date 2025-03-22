package org.example.authservice.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.example.authservice.dto.request.UserCreateRequest;
import org.example.authservice.dto.request.UserUpdateRequest;
import org.example.authservice.dto.response.UserResponse;
import org.example.authservice.entity.User;
import org.example.authservice.enumerate.Roles;
import org.example.authservice.exception.ErrorCode;
import org.example.authservice.exception.ServiceException;
import org.example.authservice.mapper.UserMapper;
import org.example.authservice.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class UserService {

    private UserRepository userRepository;
    private UserMapper userMapper;
    private PasswordEncoder passwordEncoder;

    public UserResponse createUser(UserCreateRequest userCreateRequest) {
        if (userRepository.existsByEmail(userCreateRequest.getEmail())||userRepository.existsByUsername(userCreateRequest.getUsername())) {
            throw new ServiceException(ErrorCode.USER_EXISTED);
        }
        User user = userMapper.toUser(userCreateRequest);
        user.setPassword(passwordEncoder.encode(userCreateRequest.getPassword()));

        return userMapper.toUserResponse(userRepository.save(user));
    }

    public List<UserResponse> findAllUser(){
        return userRepository.findAll().stream().map(userMapper::toUserResponse).collect(Collectors.toList());
    }

    public UserResponse findUserById(String id){
       return userMapper.toUserResponse(userRepository.findById(id).orElseThrow(()-> new ServiceException(ErrorCode.USER_NOT_EXISTED)));
    }

    public UserResponse updateUser(UserUpdateRequest userUpdateRequest, String userId){
        User user = userRepository.findById(userId).orElseThrow(() -> new ServiceException(ErrorCode.USER_NOT_EXISTED));

        userMapper.updateUser(userUpdateRequest, user);

        return userMapper.toUserResponse(userRepository.save(user));
    }

}
