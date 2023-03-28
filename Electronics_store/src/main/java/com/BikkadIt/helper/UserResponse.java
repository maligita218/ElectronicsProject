package com.BikkadIt.helper;

import com.BikkadIt.dto.UserDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
public class UserResponse {

    private List<UserDto> content;
    private Integer pageNumber;
    private Integer pageSize;

}
