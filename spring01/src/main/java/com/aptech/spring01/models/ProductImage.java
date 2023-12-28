package com.aptech.spring01.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbl_product_image")
public class ProductImage {
    @Id
    @Column(name = "product_id")
    private int product_id;

    @Id
    @Column(name = "media_id")
    private int mediaId;
}
