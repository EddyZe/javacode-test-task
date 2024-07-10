package ru.edd.javacodetesttask.util.mapper;

import ru.edd.javacodetesttask.dto.TransactionDTO;
import ru.edd.javacodetesttask.entity.Transaction;
import ru.edd.javacodetesttask.entity.Wallet;

public class TransactionMapper {


    public static Transaction convertToTransactionFromDto(TransactionDTO dto) {
        return Transaction.builder()
                .amount(dto.getAmount())
                .operationType(dto.getOperationType())
                .build();
    }
}
