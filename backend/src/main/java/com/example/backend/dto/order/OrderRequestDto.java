package com.example.backend.dto.order;

import com.example.backend.validation.PhoneNumber;
import java.math.BigDecimal;
import java.util.List;
import lombok.Data;
import lombok.experimental.Accessors;
import jakarta.validation.constraints.NotNull;

@Data
@Accessors(chain = true)
public class OrderRequestDto {
    @NotNull
    private String clientName;
    @NotNull
    @PhoneNumber
    private String phoneNumber;
    @NotNull
    private BigDecimal orderTotal;
    @NotNull
    private Long masterId;
    @NotNull
    private List<Long> servicesId;
}
