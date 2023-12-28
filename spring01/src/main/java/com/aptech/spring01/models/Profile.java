package com.aptech.spring01.models;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "tbl_profiles")
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "birthday")
    private Date birthday;

    @Column(name = "place_of_birth", length = 255)
    private String placeOfBirth;

    @Column(name = "address")
    private String address;

    @Column(name = "sex")
    private boolean sex;

    @OneToOne(mappedBy = "profile",  fetch = FetchType.EAGER)
    private User user;


    public Profile() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }
}
