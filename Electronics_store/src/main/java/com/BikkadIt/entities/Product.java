package com.BikkadIt.entities;

import jdk.jfr.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;

import java.time.LocalDateTime;
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
    @CreationTimestamp
    @Column(name = "added_date", nullable = false, updatable = false)
    private LocalDateTime addedDate;
    @Column(name = "live")
    private boolean live;

    @Column(name = "Stock")
    private boolean stock;


}
