package com.backend_project.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "product")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "product_image")
    private String productImage;
    @Column(name = "product_name")
    private String productName;
    @Column(name = "type")
    private String type;
    @Column(name = "description", length = 500)
    private String description;
    @Column(name = "price")
    private double price;
    @Column(name = "rate")
    private int rate;
    @Column(name = "sales")
    private int sales;
    @Column(name = "sale_type")
    private String saleType;
    @Column(name = "storage_address")
    private String storageAddress;
    @Column(name = "stock_number")
    private int stockNumber;
    @Column(name = "create_at")
    private Date createAt;
}
