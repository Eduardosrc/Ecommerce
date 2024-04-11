package com.theo.ecommerce_back.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "inventory")
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "stock", nullable = false)
    private Integer stock;

    @OneToOne(optional = false, orphanRemoval = true)
    @JoinColumn(name = "product_id", nullable = false, unique = true)
    private Product product;

}