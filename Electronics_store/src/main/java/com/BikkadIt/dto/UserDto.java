package com.BikkadIt.dto;


import com.BikkadIt.validator.ImageNameValid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto extends  BaseEntityDto{

    private Long userId;
    @Size(min = 3, max = 20, message = "Invalid Name!!")
    private String userName;
    @Email(message = "Invalid Email!!")
    @NotBlank
    private String email;
    @NotBlank(message = "Password is required!!")
    private String password;
    @Size(min=4,max=6,message = "Invalid Gender!!")
    private String gender;
    @NotBlank(message = "Write Something about yourself!!")
    private String about;

    //custom validator
    @ImageNameValid
    private String image;


}
