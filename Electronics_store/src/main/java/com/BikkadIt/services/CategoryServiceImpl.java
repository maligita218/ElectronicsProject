package com.BikkadIt.services;

import com.BikkadIt.dto.CategoryDto;
import com.BikkadIt.entities.Category;
import com.BikkadIt.helper.CategoryResponse;
import com.BikkadIt.repository.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryServiceI {
    @Autowired
    private CategoryRepository categoryRepo;

    Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);
    @Autowired
    private ModelMapper modelMapper;

    /**
     * @param categoryDto
     * @return
     * @apiNote This api is for save the category details
     */
    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        logger.info("Initiate dao call to create category details ");
        Category category = this.modelMapper.map(categoryDto, Category.class);
        category.setIsActive('Y');
        Category save = this.categoryRepo.save(category);
        logger.info("completed dao call to save category details ");
        return this.modelMapper.map(save, CategoryDto.class);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Long categoryId) {
        return null;
    }

    @Override
    public void deleteCategory(Long categoryId) {

    }

    @Override
    public CategoryDto getCategoryById(Long categoryId) {
        return null;
    }

    @Override
    public CategoryResponse getAllCategory(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {
        return null;
    }
}
