package com.BikkadIt.controller;

import com.BikkadIt.dto.ProductDto;
import com.BikkadIt.entities.Product;
import com.BikkadIt.helper.ApiResponse;
import com.BikkadIt.helper.ProductResponse;
import com.BikkadIt.helper.UserResponse;
import com.BikkadIt.services.ProductService;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.validation.Valid;
import javax.websocket.server.PathParam;

@RestController
@Slf4j
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    /**
     * @param productDto
     * @return
     * @author Gitanjali
     * @apiNote This Api is to save products data
     */
    @PostMapping("/")
    public ResponseEntity<ProductDto> createProduct(@Valid @RequestBody ProductDto productDto) {
        log.info("Initiating request for create a new product");
        ProductDto productDto1 = this.productService.createProduct(productDto);
        log.info("Complete request for create a new product");
        return new ResponseEntity<>(productDto1, HttpStatus.CREATED);
    }

    /**
     * @param productDto
     * @param productId
     * @return
     * @apiNote This Api is to update product details
     */

    @PutMapping("/{productId}")
    public ResponseEntity<ProductDto> updateProduct(@Valid @RequestBody ProductDto productDto, @PathVariable Long productId) {
        log.info("Initiating the request to update the product with productId:{}", productId);
        ProductDto productDto1 = this.productService.updateProduct(productDto, productId);
        log.info("Complete the request to update the product with productId:{}", productId);
        return new ResponseEntity<>(productDto1, HttpStatus.ACCEPTED);
    }

    /**
     * @param productId
     * @apiNote This Api for delete the product
     */

    @DeleteMapping("/api/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long productId) {

        log.info("Initiating request to delete product details with productId:{} ", productId);
        this.productService.deleteProduct(productId);
        ApiResponse apiResponse = ApiResponse.builder().success(true).
                message("Product deleted Successfully!!").status(HttpStatus.OK).build();
        log.info("Complete request to delete product details with productId:{} ", productId);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);

    }

    /**
     * @param @RequestParam pageNumber
     * @param @RequestParam pageSize
     * @return
     * @apiNote This Api is for getAll product details
     */
    //getAll Product
    @GetMapping("/")
    public ResponseEntity<ProductResponse> getAllProduct(
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize) {

        log.info("Initiating Request to get all products details");
        return new ResponseEntity<>(this.productService.getAllProduct(pageNumber, pageSize), HttpStatus.OK);
    }

    /**
     * @param productId
     * @return
     * @apiNote This Api is to get single product details
     */
    //getProduct by Id
    @GetMapping("/{productId}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Long productId) {
        log.info("Initiating request to get product by productId:{} ", productId);
        ProductDto productById = this.productService.getProductById(productId);
        log.info("Complete request to get product by productId:{} ", productId);
        return new ResponseEntity<>(productById, HttpStatus.OK);
    }
}
