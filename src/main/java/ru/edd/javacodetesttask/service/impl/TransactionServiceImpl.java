package ru.edd.javacodetesttask.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.edd.javacodetesttask.dto.TransactionDTO;
import ru.edd.javacodetesttask.entity.Transaction;
import ru.edd.javacodetesttask.entity.Wallet;
import ru.edd.javacodetesttask.exception.TransactionException;
import ru.edd.javacodetesttask.exception.WalletNotFoundException;
import ru.edd.javacodetesttask.repository.TransactionRepository;
import ru.edd.javacodetesttask.repository.WalletRepository;
import ru.edd.javacodetesttask.service.TransactionService;
import ru.edd.javacodetesttask.util.enums.OperationType;
import ru.edd.javacodetesttask.util.mapper.TransactionMapper;

import java.math.BigDecimal;


@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final WalletRepository walletRepository;
    private final TransactionRepository transactionRepository;

    @Override
    @Transactional
    public BigDecimal executeTransaction(TransactionDTO transactionDTO) {
        Wallet wallet = walletRepository.findById(transactionDTO.getWalletId())
                .orElseThrow((() -> new WalletNotFoundException("Кошелек с таким ID не найден!")));
        Transaction transaction = TransactionMapper.convertToTransactionFromDto(transactionDTO);

        wallet.setBalance(calculateNewBalance(transactionDTO, wallet));
        transaction.setWallet(wallet);
        transactionRepository.save(transaction);
        walletRepository.save(wallet);
        return wallet.getBalance();
    }

    private BigDecimal calculateNewBalance(TransactionDTO transactionDTO, Wallet wallet) {
        BigDecimal newBalance = new BigDecimal(0);
        BigDecimal currentWalletBalance = wallet.getBalance();

        if (transactionDTO.getOperationType() == OperationType.DEPOSIT)
            newBalance = currentWalletBalance.add(transactionDTO.getAmount());

        if (transactionDTO.getOperationType() == OperationType.WITHDRAW) {
            if (currentWalletBalance.compareTo(transactionDTO.getAmount()) < 0)
                throw new TransactionException("Недостаточно средств!");

            newBalance = currentWalletBalance.subtract(transactionDTO.getAmount());
        }
        return newBalance;
    }
}
