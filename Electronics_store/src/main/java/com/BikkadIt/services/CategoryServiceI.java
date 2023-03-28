package com.BikkadIt.services;

import com.BikkadIt.dto.CategoryDto;
import com.BikkadIt.helper.CategoryResponse;

public interface CategoryServiceI {

    //Create
    CategoryDto createCategory(CategoryDto categoryDto);

    //update
    CategoryDto updateCategory(CategoryDto categoryDto, Long categoryId);

    //delete
    void deleteCategory(Long categoryId);

    //get single category by id
    CategoryDto getCategoryById(Long categoryId);

    //get All category
    CategoryResponse getAllCategory(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);
}
