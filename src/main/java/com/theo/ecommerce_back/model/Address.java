package com.theo.ecommerce_back.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private LocalUser localUser;

    @Column(name = "address_line", nullable = false, length = 512)
    private String addressLine;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false, length = 75)
    private String country;

    @Column(name = "post_code", nullable = false)
    private String postCode;

    @Column(nullable = false)
    private Boolean active;

}
