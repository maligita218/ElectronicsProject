package com.BikkadIt.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto extends CategoryBaseEntityDto {

    private Long categoryId;
    @Size(min = 5, max = 20, message = "Please Enter Proper Title!!")
    private String categoryTitle;
    @NotBlank(message = "Write Description Here!!")
    private String CategoryDescription;
}
