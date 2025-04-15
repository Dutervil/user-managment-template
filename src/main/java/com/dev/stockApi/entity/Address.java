package com.dev.stockApi.entity;


import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Address {

    private String address1;
    private String address2;
    private String city;
    private String zip;
    private String country;


}
