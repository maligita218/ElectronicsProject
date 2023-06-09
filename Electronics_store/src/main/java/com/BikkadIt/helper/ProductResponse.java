package com.BikkadIt.helper;

import com.BikkadIt.dto.CategoryDto;
import com.BikkadIt.dto.ProductDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {

    private List<ProductDto> content;
    private Integer pageNumber;
    private Integer pageSize;

    private Long totalElements;

    private Integer totalPages;

}
