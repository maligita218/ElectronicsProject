package com.BikkadIt.controller;

import com.BikkadIt.dto.CategoryDto;
import com.BikkadIt.services.CategoryServiceI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryServiceI categoryServiceI;

    Logger logger = LoggerFactory.getLogger(CategoryController.class);

    //create category
    @PostMapping("/")
    public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto){
        logger.info("Initiating request for create a new category");
        CategoryDto categoryDto1 = this.categoryServiceI.createCategory(categoryDto);
        logger.info("Completing request for create a new category");
        return new ResponseEntity<>(categoryDto1, HttpStatus.CREATED);
    }
}
