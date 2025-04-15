package com.dev.stockApi.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class SupplierOrder extends Auditable{

    private String OrderId;
    private Instant OrderDate;

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;


   @OneToMany(mappedBy = "supplierOrder")
    private List<SupplierOrderItem> items;

}
