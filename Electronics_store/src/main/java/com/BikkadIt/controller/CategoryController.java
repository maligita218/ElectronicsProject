package com.BikkadIt.controller;

import com.BikkadIt.dto.CategoryDto;
import com.BikkadIt.dto.UserDto;
import com.BikkadIt.helper.ApiResponse;
import com.BikkadIt.helper.CategoryResponse;
import com.BikkadIt.helper.ImageResponse;
import com.BikkadIt.helper.UserResponse;
import com.BikkadIt.services.CategoryServiceI;
import com.BikkadIt.services.FileService;
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
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
/**
 * @author Gitanjali
 * @apiNote This api is related to save retrieve delete records
 *
 *
 */

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryServiceI categoryServiceI;
    @Autowired
    private FileService fileService;

    @Value("${category.profile.image.path}")
    private String imageUploadPath;
    Logger logger = LoggerFactory.getLogger(CategoryController.class);

    /**
     * @author Gitanjali
     * @apiNote This Api is to save category data
     * @param categoryDto
     * @return
     */
    //create category
    @PostMapping("/")
    public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto) {
        logger.info("Initiating request for create a new category");
        CategoryDto categoryDto1 = this.categoryServiceI.createCategory(categoryDto);
        logger.info("Completing request for create a new category");
        return new ResponseEntity<>(categoryDto1, HttpStatus.CREATED);
    }

    /**
     * @apiNote This Api is to update category details
     * @param categoryDto
     * @param categoryId
     * @return
     */

    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto, @PathVariable Long categoryId) {
        logger.info("Initiating the request to update the category with categoryId:{}", categoryId);
        CategoryDto categoryDto1 = this.categoryServiceI.updateCategory(categoryDto, categoryId);
        logger.info("Complete the request related to update");
        return new ResponseEntity<>(categoryDto1, HttpStatus.ACCEPTED);
    }

    /**
     * @apiNote This Api for delete the category
     * @param categoryId
     *
     */
    @DeleteMapping("/{categoryId}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long categoryId) {
        logger.info("Initiating request to delete user details with categoryId:{} ", categoryId);
        this.categoryServiceI.deleteCategory(categoryId);
        ApiResponse apiResponse = ApiResponse.builder()
                .message("Category Deleted Successfully!!")
                .success(true).status(HttpStatus.OK).build();
        logger.info("Completed request to delete user details with categoryId:{} ", categoryId);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);

    }
    /**
     * @apiNote This Api is for getAll category details
     * @return
     */
    @GetMapping()
    public ResponseEntity<CategoryResponse> getAllCategory(
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize,
            @RequestParam(value = "sortBy", defaultValue = "categoryId", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir) {

        logger.info("Initiating Request to get all users details");
        return new ResponseEntity<CategoryResponse>(this.categoryServiceI.getAllCategory(pageNumber, pageSize, sortBy, sortDir), HttpStatus.OK);
    }

    /**
     * @apiNote This Api is to get single category details
     * @param categoryId
     * @return
     *
     */

    @GetMapping("/id/{categoryId}")
    public ResponseEntity<CategoryDto> getSingleCategoryById(@PathVariable Long categoryId) {

        logger.info("Initiating Request to get single category details");
        return new ResponseEntity<>(this.categoryServiceI.getCategoryById(categoryId), HttpStatus.OK);
    }
    /**
     * @apiNote This Api is to upload image
     * @param categoryId
     * @param image
     * @return
     *
     */
    //upload Category Image
    @PostMapping("/image/{categoryId}")
    public ResponseEntity<ImageResponse> uploadImage(@RequestParam("coverImage") MultipartFile image, @PathVariable Long categoryId) throws IOException {

        String imagename = fileService.uploadFile(image, imageUploadPath);

        CategoryDto category = this.categoryServiceI.getCategoryById(categoryId);
        category.setCoverImage(imagename);
        CategoryDto categoryDto = this.categoryServiceI.updateCategory(category, categoryId);
        ImageResponse imageResponse=ImageResponse.builder().imageName(imagename).success(true).status(HttpStatus.CREATED).build();
        return new ResponseEntity<>(imageResponse,HttpStatus.CREATED);
    }

    /**
     * @apiNote This Api is to serve image
     * @param response
     * @param categoryId
     * @return
     *
     */
    @GetMapping("/image/{categoryId}")
    public void serveUserImage(@PathVariable Long categoryId, HttpServletResponse response) throws IOException {

        CategoryDto categoryDto = categoryServiceI.getCategoryById(categoryId);
        logger.info("User image name:{}", categoryDto.getCoverImage());
        InputStream resource = fileService.getResource(imageUploadPath, categoryDto.getCoverImage());
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource, response.getOutputStream());
    }
}
