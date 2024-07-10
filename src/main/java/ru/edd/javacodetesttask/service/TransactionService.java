package ru.edd.javacodetesttask.service;

import ru.edd.javacodetesttask.dto.TransactionDTO;

import java.math.BigDecimal;

public interface TransactionService {

    BigDecimal executeTransaction(TransactionDTO transactionDTO);

}
