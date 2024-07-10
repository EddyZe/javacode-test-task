package ru.edd.javacodetesttask.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WalletDTO {

    @JsonProperty("walletId")
    private UUID walletId;

    @JsonProperty("balance")
    private BigDecimal balance;
}
