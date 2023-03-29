package com.BikkadIt.services;

import com.BikkadIt.dto.CategoryDto;
import com.BikkadIt.entities.Category;
import com.BikkadIt.exception.ResourceNotFoundException;
import com.BikkadIt.helper.CategoryResponse;
import com.BikkadIt.repository.CategoryRepository;
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

        Category save = this.categoryRepo.save(category);
        logger.info("completed dao call to save category details ");
        return this.modelMapper.map(save, CategoryDto.class);
    }

    /**
     * @param categoryDto
     * @return
     * @Param categoryId
     * @apiNote This api is for update the category details
     */
    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Long categoryId) {
        logger.info("Initiate dao call to update category details with categoryId:{} ", categoryId);
        Category category = this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category not found on server:" + categoryId));

        category.setCategoryTitle(categoryDto.getCategoryTitle());
        category.setCategoryDescription(categoryDto.getCategoryDescription());
        category.setCoverImage(categoryDto.getCoverImage());

        Category updateCategory = this.categoryRepo.save(category);
        logger.info("Completed dao call to update category details with:{} ", categoryId);
        return this.modelMapper.map(updateCategory, CategoryDto.class);
    }

    /**
     * @param categoryId
     * @apiNote This api is for delete the category details
     */
    @Override
    public void deleteCategory(Long categoryId) {
        logger.info("Initiate dao call to delete category details with categoryId:{} ", categoryId);
        Category category = this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category not found on server:" + categoryId));
        logger.info("Completed dao call to delete category details with:{} ", categoryId);
        this.categoryRepo.delete(category);
    }

    /**
     * @param categoryId
     * @return
     * @apiNote This api is for det the category details by id
     */

    @Override
    public CategoryDto getCategoryById(Long categoryId) {

        logger.info("Initiate dao call to get user details by categoryId:{} ", categoryId);
        Category category = this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category not found on server:" + categoryId));
        logger.info("Completed dao call to get category by :{} ", categoryId);
        return this.modelMapper.map(category, CategoryDto.class);
    }

    /**
     * @param pageNumber
     * @return
     * @Param pageSize
     * @Param sortBy
     * @Param sortDir
     * @apiNote This api is for get all the category details
     */
    @Override
    public CategoryResponse getAllCategory(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {
        logger.info("Initiate dao call to get all category details");
        Sort sort = null;
        if (sortDir.equalsIgnoreCase("asc")) {
            sort = Sort.by(sortBy).ascending();
        } else {
            sort = Sort.by(sortBy).descending();
        }

        PageRequest pg = PageRequest.of(pageNumber, pageSize, sort);

        Page<Category> categoryPage = this.categoryRepo.findAll(pg);
        List<Category> allcategory = categoryPage.getContent();

        List<CategoryDto> categorydto = allcategory.stream().map((category -> this.modelMapper.map(category, CategoryDto.class))).collect(Collectors.toList());

        CategoryResponse categoryResponse = new CategoryResponse();

        categoryResponse.setPageNumber(categoryPage.getNumber());
        categoryResponse.setPageSize(categoryPage.getSize());
        categoryResponse.setTotalPages(categoryPage.getTotalPages());
        categoryResponse.setTotalElements(categoryPage.getTotalElements());
        categoryResponse.setContent(categorydto);
        logger.info("Completed dao call to get all category details");
        return categoryResponse;
    }


}
