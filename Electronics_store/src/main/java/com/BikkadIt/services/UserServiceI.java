package com.BikkadIt.services;

import com.BikkadIt.dto.UserDto;
import com.BikkadIt.helper.UserResponse;

import java.util.List;

public interface UserServiceI {

    //Create User

    UserDto createUser(UserDto userDto);

    //Update User
    UserDto updateUser(UserDto userDto, Long userId);

    //Delete User
    void deleteUser(Long userId);

    //Get All Users
    UserResponse getAllUsers(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);

    //Get Single User By Id
    UserDto getUserById(Long userId);

    //Get Single User By email
    UserDto getUserByEmail(String email);
    //search user
}
