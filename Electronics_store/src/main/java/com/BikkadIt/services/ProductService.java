package com.BikkadIt.services;

import com.BikkadIt.dto.ProductDto;
import com.BikkadIt.helper.ProductResponse;
import org.apache.tomcat.jni.Proc;


import java.util.List;


public interface ProductService {
    //create
    ProductDto createProduct(ProductDto productDto);

    //update
    ProductDto updateProduct(ProductDto productDto, Long productId);

    //delete
    public void deleteProduct(Long productId);

    //get all product
    ProductResponse getAllProduct(int pageNumber, int pageSize);

    //get single
    ProductDto getProductById(Long productId);

    //get all:live
    ProductResponse getAllLive(int pageNumber,int pageSize);

    //serach product
 ProductResponse serachByTitle(String subTitle,int pageNumber,int pageSize);


}
