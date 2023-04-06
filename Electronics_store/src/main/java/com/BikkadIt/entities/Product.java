package com.BikkadIt.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;
    private String title;

    private String description;

    private int price;

    private int discountedPrice;

    private int quantity;



}
