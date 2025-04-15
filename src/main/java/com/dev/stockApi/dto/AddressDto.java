package com.dev.stockApi.dto;


import com.dev.stockApi.entity.Address;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddressDto {


    private String address1;
    private String address2;
    private String city;
    private String zip;
    private String country;

    public static AddressDto fromEntity(Address address) {
        if (address == null) {
            return null;
        }

        return AddressDto.builder()
                .address1(address.getAddress1())
                .address2(address.getAddress2())
                .zip(address.getZip())
                .city(address.getCity())
                .country(address.getCountry())
                .build();
    }

    public static Address toEntity(AddressDto addressDTO) {
        if (addressDTO == null) {
            return null;
        }
        Address address = new Address();
        address.setAddress1(addressDTO.getAddress1());
        address.setAddress2(addressDTO.getAddress2());
        address.setZip(addressDTO.getZip());
        address.setCity(addressDTO.getCity());
        address.setCountry(addressDTO.getCountry());
        return address;
    }
}
