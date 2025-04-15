package com.dev.stockApi.entity;

import jakarta.persistence.Entity;
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
public class Sale extends Auditable {

    private String code;
    private Instant SaleDate;
    private String comment;
    private Integer companyId;
    @OneToMany(mappedBy = "sale")
    private List<SaleItem> itemList;
}
