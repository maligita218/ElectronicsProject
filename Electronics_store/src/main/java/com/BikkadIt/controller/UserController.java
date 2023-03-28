package com.BikkadIt.controller;


import com.BikkadIt.dto.UserDto;
import com.BikkadIt.helper.ApiResponse;
import com.BikkadIt.helper.ImageResponse;
import com.BikkadIt.helper.UserResponse;
import com.BikkadIt.services.FileService;
import com.BikkadIt.services.UserServiceI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserServiceI userServiceI;
    @Autowired
    private FileService fileService;
    @Value("${user.profile.image.path}")
    private String imageUploadPath;

    Logger logger = LoggerFactory.getLogger(UserController.class);

    //create new user
    @PostMapping("/")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
        logger.info("Initiating request for create a new User");
        UserDto userDto1 = this.userServiceI.createUser(userDto);
        logger.info("Fulfill the request of create User");
        return new ResponseEntity<>(userDto1, HttpStatus.CREATED);
    }

    //update user
    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable("userId") Long userId) {
        logger.info("Initiating the request to update the user with userId:{}", userId);
        UserDto updateUser = this.userServiceI.updateUser(userDto, userId);
        logger.info("Complete the request related to update");
        return new ResponseEntity<>(updateUser, HttpStatus.ACCEPTED);

    }

    //delete user
    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable("userId") Long userId) {
        logger.info("Initiating request to delete user details with userId:{} ", userId);
        this.userServiceI.deleteUser(userId);
        ApiResponse apiResponse = ApiResponse.builder()
                .message("User Deleted Successfully!!").
                success(true).status(HttpStatus.OK).build();
        logger.info("Completed request to delete user details with userId:{} ", userId);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    //get all users
    @GetMapping("/")
    public ResponseEntity<UserResponse> getAllUsers(
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize,
            @RequestParam(value = "sortBy", defaultValue = "userId", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir) {

        logger.info("Initiating Request to get all users details");
        return new ResponseEntity<UserResponse>(this.userServiceI.getAllUsers(pageNumber, pageSize, sortBy, sortDir), HttpStatus.OK);
    }

    //get user by id
    @GetMapping("/api/{userId}")
    public ResponseEntity<UserDto> getSingleUserById(@PathVariable Long userId) {
        logger.info("Initiating Request to get single users details");
        return new ResponseEntity<>(this.userServiceI.getUserById(userId), HttpStatus.OK);


    }

    //get user by email
    @GetMapping("/{email}")
    public ResponseEntity<UserDto> getUserByEmail(@PathVariable String email) {
        logger.info("Initiating Request to get users by email");
        return new ResponseEntity<>(this.userServiceI.getUserByEmail(email), HttpStatus.OK);

    }

    //upload user image
    @PostMapping("/image/{userId}")
    public ResponseEntity<ImageResponse> uploadUserImage(@RequestParam("userImage") MultipartFile image, @PathVariable Long userId) throws IOException {

        String imageName = fileService.uploadFile(image, imageUploadPath);

        UserDto user = userServiceI.getUserById(userId);
        user.setImage(imageName);
        UserDto userDto = userServiceI.updateUser(user, userId);

        ImageResponse imageResponse = ImageResponse.builder().imageName(imageName).success(true).status(HttpStatus.CREATED).build();
        return new ResponseEntity<>(imageResponse, HttpStatus.CREATED);

    }

    //serve user image
    @GetMapping("/image/{userId}")
    public void serveUserImage(@PathVariable Long userId, HttpServletResponse response) throws IOException {

        UserDto user = userServiceI.getUserById(userId);
        logger.info("User image name:{}", user.getImage());
        InputStream resource = fileService.getResource(imageUploadPath, user.getImage());
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);

        StreamUtils.copy(resource, response.getOutputStream());
    }
}
