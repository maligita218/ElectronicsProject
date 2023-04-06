package com.BikkadIt.services;

import com.BikkadIt.dto.ProductDto;
import org.apache.tomcat.jni.Proc;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface ProductService {
    //create
    ProductDto createProduct(ProductDto productDto);

    //update
    ProductDto updateProduct(ProductDto productDto, Long productId);

    //delete
    public void deleteProduct(Long productId);

    //get all product
    List<ProductDto> getAllProduct();

    //get by Id
    ProductDto getProductById(Long productId);


}
