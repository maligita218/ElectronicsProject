package com.BikkadIt.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;
    @Column(name = "Title")
    private String title;
    @Column(name = "Description")
    private String description;
    @Column(name = "Price")
    private int price;
    @Column(length = 10000)
    private int discountedPrice;

    @Column(name = "Quantity")
    private int quantity;
    private Date addedDate;
    @Column(name = "is_live")
    private boolean islive;

    @Column(name = "Stoke")
    private boolean stock;


}
