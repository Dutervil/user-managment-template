package com.dev.stockApi.dto;


import com.dev.stockApi.entity.CustomerOrderItem;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;


@Data
@Builder
public class CustomerOrderItemDTO {

   private Long id;
   private ProductDto product;
   private CustomerOrderDto customerOrder;
    private BigDecimal quantity;
    private BigDecimal unitPrice;
    private Integer companyId;

    public static CustomerOrderItemDTO fromEntity(CustomerOrderItem customerOrderItem) {
        if (customerOrderItem == null) {
            return null;
        }

       return  CustomerOrderItemDTO.builder()
               .id(customerOrderItem.getId())
               .product(ProductDto.fromEntity(customerOrderItem.getProduct()))
               .quantity(customerOrderItem.getQuantity())
               .unitPrice(customerOrderItem.getUnitPrice())
               .companyId(customerOrderItem.getCompanyId())
               .build();
    }

public static CustomerOrderItem toEntity(CustomerOrderItemDTO customerOrderItemDTO) {
        if (customerOrderItemDTO == null) {
            return null;
        }

    CustomerOrderItem customerOrderItem = new CustomerOrderItem();
        customerOrderItem.setId(customerOrderItemDTO.getId());
        customerOrderItem.setProduct(ProductDto.toEntity(customerOrderItemDTO.getProduct()));
        customerOrderItem.setQuantity(customerOrderItemDTO.getQuantity());
        customerOrderItem.setUnitPrice(customerOrderItemDTO.getUnitPrice());
        customerOrderItem.setCompanyId(customerOrderItemDTO.getCompanyId());
        return customerOrderItem;
     }

}
