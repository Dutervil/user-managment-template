package com.dev.stockApi.dtorequest;

import java.io.File;
import java.math.BigDecimal;

public class ProductDtoRequest {

    private String code;
    private String name;
    private BigDecimal unitPriceExclTax;
    private BigDecimal unitPriceInclTax;
    private BigDecimal taxRate;
    private Integer quantity;
    private Long categoryId;
}
