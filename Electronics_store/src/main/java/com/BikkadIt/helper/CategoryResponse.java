package com.BikkadIt.helper;

import com.BikkadIt.dto.CategoryDto;
import com.BikkadIt.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryResponse {

    private List<CategoryDto> content;
    private Integer pageNumber;
    private Integer pageSize;

    private Long totalElements;

    private Integer totalPages;


}
