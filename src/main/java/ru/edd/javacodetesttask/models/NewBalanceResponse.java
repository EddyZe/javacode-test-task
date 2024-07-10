package ru.edd.javacodetesttask.models;


import lombok.*;
import org.springframework.http.HttpStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class NewBalanceResponse {

    private HttpStatus status;
    private BigDecimal newBalance;
    private LocalDateTime timestamp;

}
