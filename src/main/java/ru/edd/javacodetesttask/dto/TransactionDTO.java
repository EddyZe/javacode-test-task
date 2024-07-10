package ru.edd.javacodetesttask.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import ru.edd.javacodetesttask.util.enums.OperationType;

import java.math.BigDecimal;
import java.util.UUID;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class TransactionDTO {

    @NotNull(message = "Укажите UUID кошелька!")
    private UUID walletId;

    @NotNull(message = "Укажите тип операции!")
    private OperationType operationType;

    @NotNull(message = "Укажите сумму перевода!")
    @Min(value = 1, message = "Сумма не может быть меньше чем 1!")
    private BigDecimal amount;

}
