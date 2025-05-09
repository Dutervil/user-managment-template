package com.dev.stockApi.dto;

import com.dev.stockApi.entity.Address;
import com.dev.stockApi.entity.Supplier;
import jakarta.persistence.Embedded;
import org.springframework.beans.BeanUtils;

public class SupplierDto {

    private String firstName;
    private String lastName;
    private String photo;
    private String email;
    @Embedded
    private Address address;
    private String phone;

    public static SupplierDto fromEntity(Supplier supplier) {
        if (supplier == null) {
            return null;
        }

        SupplierDto dto = new SupplierDto();
        BeanUtils.copyProperties(supplier, dto);
        return dto;
    }

    public static Supplier toEntity(SupplierDto dto) {
        if (dto == null) {
            return null;
        }

        Supplier supplier = new Supplier();
        BeanUtils.copyProperties(dto, supplier);
        return supplier;
    }

}
