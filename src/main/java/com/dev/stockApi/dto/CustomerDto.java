package com.dev.stockApi.dto;

import com.dev.stockApi.entity.Customer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Embedded;
import org.springframework.beans.BeanUtils;

import java.util.List;

public class CustomerDto {

    private String firstName;
    private String lastName;
    private String photo;
    private String email;
    @Embedded
    private AddressDto address;
    private String phone;
    private Integer companyId;
    @JsonIgnore
    private List<CustomerOrderDto> customerOrders;

    public static CustomerDto fromEntity(Customer customer) {

        if (customer == null) {
            return null;
        }
        CustomerDto customerDto = new CustomerDto();
        BeanUtils.copyProperties(customer, customerDto);
        return customerDto;
    }

    public static CustomerDto fromEntity(Customer customer, List<CustomerOrderDto> customerOrders) {
        if (customer == null) {
            return null;
        }
        CustomerDto customerDto = new CustomerDto();
        BeanUtils.copyProperties(customer, customerDto);
        customerDto.customerOrders = customerOrders;
        return customerDto;
    }

    public static  Customer toEntity(CustomerDto customerDto) {
        if (customerDto == null) {
            return null;
        }
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDto, customer);
        return customer;
    }


}
