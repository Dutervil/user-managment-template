package com.dev.stockApi.dto;


import com.dev.stockApi.entity.Customer;
import com.dev.stockApi.enumeration.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.time.Instant;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerOrderDto {

    private Integer id;
    private String code;
    private Instant OrderDate;
    private Integer companyId;
    Customer customer;
    private OrderStatus orderStatus;
    private List<CustomerOrderItemDTO> orderItems;

    public static CustomerOrderDto fromEntity(Customer customer) {
       if (customer == null) {
           return null;
       }
       CustomerOrderDto orderDto=new CustomerOrderDto();
       BeanUtils.copyProperties(customer, orderDto);
       return orderDto;
    }

}
