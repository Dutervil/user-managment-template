package com.dev.stockApi.entity;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
public class Supplier extends Auditable {

    private String firstName;
    private String lastName;
    private String email;
    @Embedded
    private Address address;
    private String phone;
    @OneToMany(mappedBy = "supplier",fetch = FetchType.LAZY)
    private List<SupplierOrder>supplierOrders;
}
