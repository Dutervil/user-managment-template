package com.dev.stockApi.entity;


 import com.dev.stockApi.enumeration.StockMovementSource;
 import com.dev.stockApi.enumeration.StockMovementType;
 import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class StockMovement extends Auditable {

    private Instant MovementDate;
    private BigDecimal quantity;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    @Enumerated(EnumType.STRING)
    private StockMovementType type;
    @Enumerated(EnumType.STRING)
    private StockMovementSource source;
    private Integer companyId;
}
