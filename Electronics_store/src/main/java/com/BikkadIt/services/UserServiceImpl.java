package com.BikkadIt.services;

import com.BikkadIt.dto.UserDto;
import com.BikkadIt.entities.User;
import com.BikkadIt.exception.ResourceNotFoundException;
import com.BikkadIt.helper.UserResponse;
import com.BikkadIt.repository.UserRepository;
import org.modelmapper.ModelMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserServiceI {
    @Autowired
    private UserRepository userRpo;
    @Autowired
    private ModelMapper model;

    Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);


    @Override
    public UserDto createUser(UserDto userDto) {
        logger.info("Initiate dao call to save user details ");
        User user = this.DtoToUSer(userDto);
        User saveuser = this.userRpo.save(user);
        user.setIsActive('Y');
        logger.info("Complete dao call to save user details ");
        return this.UserToDto(saveuser);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Long userId) {
        logger.info("Initiate dao call to update user details with userId:{} ", userId);
        User user = this.userRpo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found on server:" + userId));

        user.setUserName(userDto.getUserName());
        user.setEmail(userDto.getEmail());
        user.setAbout(userDto.getAbout());
        user.setGender(userDto.getGender());
        user.setPassword(userDto.getPassword());
        user.setImage(userDto.getImage());

        User updateuser = this.userRpo.save(user);
        logger.info("Completed dao call to update user details with:{} ", userId);
        return this.UserToDto(updateuser);
    }

    @Override
    public void deleteUser(Long userId) {
        logger.info("Initiate dao call to delete user details with userId:{} ", userId);
        User user1 = this.userRpo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("AppConstants.Not_Found"));
      //  user1.setIsActive('N');
        logger.info("Completed dao call to delete user details with userId:{} ", userId);
        this.userRpo.delete(user1);

    }

    @Override
    public UserResponse getAllUsers(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {
        logger.info("Initiate dao call to get all user details");
        Sort sort = null;
        if (sortDir.equalsIgnoreCase("asc")) {
            sort = Sort.by(sortBy).ascending();
        } else {
            sort = Sort.by(sortBy).descending();
        }
        PageRequest p = PageRequest.of(pageNumber, pageSize, sort);

        Page<User> preuser = this.userRpo.findAll(p);
        List<User> allusers = preuser.getContent();

        List<UserDto> userdto = allusers.stream().map((user) -> this.model.map(user, UserDto.class)).collect(Collectors.toList());

        UserResponse userResponse = new UserResponse();
        userResponse.setPageNumber(preuser.getNumber());
        userResponse.setPageSize(preuser.getSize());
        userResponse.setContent(userdto);


        logger.info("Completed dao call to get all user details");
        return userResponse;
    }

    @Override
    public UserDto getUserById(Long userId) {
        logger.info("Initiate dao call to get user details with userId:{} ", userId);
        User user2 = this.userRpo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found on server" + userId));
        logger.info("Completed dao call to get user details with:{} ", userId);
        return this.UserToDto(user2);
    }


    @Override
    public UserDto getUserByEmail(String email) {
        logger.info("Initiate dao call to get user details with email:{} ", email);
        User email1 = this.userRpo.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("Email not found on server" + email));
        logger.info("Completed dao call to get user details with email:{} ", email);
        return this.UserToDto(email1);
    }

    public User DtoToUSer(UserDto userDto) {

        User user = this.model.map(userDto, User.class);
        return user;

    }

    public UserDto UserToDto(User user) {

        UserDto userDto = this.model.map(user, UserDto.class);
        return userDto;
    }
}

