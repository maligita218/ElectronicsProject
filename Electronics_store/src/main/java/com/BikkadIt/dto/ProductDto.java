package com.BikkadIt.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    private Long productId;
    @Size(min = 5, max = 20, message = "Please Enter Proper Title!!")
    private String title;
    @NotBlank(message = "Description Required!!")
    private String description;
    @NotBlank(message = "Enter Price!!")
    private int price;
    @NotBlank(message = "Please tell about discount!!")
    private int discountedPrice;
    @NotBlank(message = "Quantity Required!!")
    private int quantity;
}
