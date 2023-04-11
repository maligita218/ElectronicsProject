package com.BikkadIt.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "User_Info")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name = "User_Name")
    private String userName;

    @Column(name = "User_Email", unique = true)
    private String email;
    @Column(name = "Password")
    private String password;

    @Column(name = "Gender")
    private String gender;

    private String about;
    @Column(name = "User_iamge")
    private String image;


    public User(int i, String gita, String s, String acd, String female, String iAmEngg) {
    }
}
