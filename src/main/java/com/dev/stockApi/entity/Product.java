package com.dev.stockApi.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product extends Auditable{


    private String code;
    private String name;
    private BigDecimal unitPriceExclTax;
    private BigDecimal unitPriceInclTax;
    private BigDecimal taxRate;
    private String photoUrl;
    private Integer quantity;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;


}
