package com.BikkadIt.services;

import com.BikkadIt.dto.ProductDto;
import com.BikkadIt.entities.Product;
import com.BikkadIt.exception.ResourceNotFoundException;
import com.BikkadIt.helper.ProductResponse;
import com.BikkadIt.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepo;
    @Autowired
    private ModelMapper modelMapper;

    /**
     * @param productDto
     * @return
     * @apiNote This api is for save the product details
     */
    @Override
    public ProductDto createProduct(ProductDto productDto) {
        log.info("Initiate dao call to create product details:");
        Product product = this.modelMapper.map(productDto, Product.class);
        Product saveproduct = this.productRepo.save(product);
        log.info("completed dao call to save product details:");
        return this.modelMapper.map(saveproduct, ProductDto.class);
    }

    /**
     * @param productDto
     * @return
     * @Param productId
     * @apiNote This api is for update the product details
     */
    @Override
    public ProductDto updateProduct(ProductDto productDto, Long productId) {
        log.info("Initiate dao call to update product details with productId:{} ", productId);
        Product product = this.productRepo.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product not found on server:" + productId));

        product.setDescription(productDto.getDescription());
        product.setTitle(productDto.getTitle());
        product.setQuantity(productDto.getQuantity());
        product.setPrice(productDto.getPrice());
        product.setDiscountedPrice(productDto.getDiscountedPrice());
        product.setIslive(productDto.isIslive());
        product.setStock(productDto.isStock());

        Product updateProduct = this.productRepo.save(product);
        log.info("complete dao call to update product details with productId:{} ", productId);
        return this.modelMapper.map(updateProduct, ProductDto.class);

    }

    /**
     * @param productId
     * @apiNote This api is for delete the product details
     */

    @Override
    public void deleteProduct(Long productId) {
        log.info("Initiate dao call to delete product with productId:{} ", productId);
        Product product = this.productRepo.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product not found on server:" + productId));

        log.info("complete dao call to delete product with productId:{} ", productId);
        this.productRepo.delete(product);

    }

    /**
     * @param pageNumber
     * @param pageSize
     * @return pageResponse
     * @apiNote This api is for get all the product details
     */
    @Override
    public ProductResponse getAllProduct(int pageNumber, int pageSize) {
        log.info("Initiate dao call to get all product Details:");

        PageRequest p = PageRequest.of(pageNumber, pageSize);
        Page<Product> productPage = this.productRepo.findAll(p);
        List<Product> allproduct = productPage.getContent();
        List<ProductDto> productDtos = allproduct.stream().map(product -> this.modelMapper.map(allproduct, ProductDto.class)).collect(Collectors.toList());

        ProductResponse response = new ProductResponse();

        response.setPageNumber(productPage.getNumber());
        response.setPageSize(productPage.getSize());
        response.setTotalPages(productPage.getTotalPages());
        response.setTotalElements(productPage.getTotalElements());
        response.setContent(productDtos);
        log.info("Complete dao call to get all product Details:");
        return response;
    }

    /**
     * @param productId
     * @return
     * @apiNote This api is for get the product details by id
     */
    @Override
    public ProductDto getProductById(Long productId) {
        log.info("Initiate dao call to get product By productId:{}", productId);
        Product product = this.productRepo.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product not found on server:" + productId));
        log.info("Completed dao call to get product by :{} ", productId);
        return this.modelMapper.map(product, ProductDto.class);
    }

    @Override
    public ProductResponse getAllLive(int pageNumber,int pageSize) {

        PageRequest p = PageRequest.of(pageNumber, pageSize);
        Page<Product> productPage = this.productRepo.findByLiveTrue(p);
        List<Product> allproduct = productPage.getContent();
        List<ProductDto> productDtos = allproduct.stream().map(product -> this.modelMapper.map(allproduct, ProductDto.class)).collect(Collectors.toList());

        ProductResponse response = new ProductResponse();

        response.setPageNumber(productPage.getNumber());
        response.setPageSize(productPage.getSize());
        response.setTotalPages(productPage.getTotalPages());
        response.setTotalElements(productPage.getTotalElements());
        response.setContent(productDtos);
        return response;
    }

    @Override
    public ProductResponse serachByTitle(String subTitle,int pageNumber,int pageSize) {
        PageRequest p = PageRequest.of(pageNumber, pageSize);
        Page<Product> productPage = this.productRepo.findByTitleContaining(subTitle,p);
        List<Product> allproduct = productPage.getContent();
        List<ProductDto> productDtos = allproduct.stream().map(product -> this.modelMapper.map(allproduct, ProductDto.class)).collect(Collectors.toList());

        ProductResponse response = new ProductResponse();

        response.setPageNumber(productPage.getNumber());
        response.setPageSize(productPage.getSize());
        response.setTotalPages(productPage.getTotalPages());
        response.setTotalElements(productPage.getTotalElements());
        response.setContent(productDtos);
        return response ;
    }


}
