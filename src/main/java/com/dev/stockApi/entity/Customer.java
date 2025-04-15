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

public class Customer extends Auditable{

    private String firstName;
    private String lastName;
    private String photo;
    private String email;
    @Embedded
    private Address address;
    private String phone;
    @OneToMany(mappedBy = "customer")
    private List<CustomerOrder>customerOrders;
    private Integer companyId;


}
