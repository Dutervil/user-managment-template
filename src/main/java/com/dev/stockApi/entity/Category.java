package com.dev.stockApi.entity;

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
public class Category  extends Auditable{


    private String name;
    private String imageUrl;

    @OneToMany(mappedBy ="category" )
    private List<Product> products;


}
