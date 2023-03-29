package com.BikkadIt.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "categories")
public class Category extends CategoryBaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;
    @Column(name = "Cat_Title")
    private String categoryTitle;
    @Column(name = "Cat_Description")
    private String CategoryDescription;
    @Column(name="Cat_Image")
    private String CoverImage;
}
