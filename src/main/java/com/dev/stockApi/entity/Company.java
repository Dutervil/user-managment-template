package com.dev.stockApi.entity;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class Company extends Auditable{


    private String name;
    private String description;
    @Embedded
    private Address address;
    private String taxCode;
    private String photo;
    private String email;
    private String phone;
    private String web;

}
