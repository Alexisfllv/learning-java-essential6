package edu.com.javaesencial07salesapi.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name = "id_product")
    private Long idProduct;

    @Column(name = "product_name",nullable = false, length = 50)
    private String productName;

    @Column(name = "product_price",nullable = false)
    private BigDecimal productPrice;

    @Column(name = "product_description",nullable = false, length = 250)
    private String productDescription;

    @Column(name = "product_enabled",nullable = false)
    private Boolean productEnabled;

    // fk

    @ManyToOne
    @JoinColumn(name = "id_category", nullable = false
    , foreignKey = @ForeignKey(name = "FK_PRODUCT_CATEGORY"))
    private Category category;

}
