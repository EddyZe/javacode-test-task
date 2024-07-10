package ru.edd.javacodetesttask.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.edd.javacodetesttask.dto.TransactionDTO;
import ru.edd.javacodetesttask.dto.WalletDTO;
import ru.edd.javacodetesttask.entity.Wallet;
import ru.edd.javacodetesttask.exception.TransactionException;
import ru.edd.javacodetesttask.models.NewBalanceResponse;
import ru.edd.javacodetesttask.service.TransactionService;
import ru.edd.javacodetesttask.service.WalletService;
import ru.edd.javacodetesttask.util.mapper.WalletMapper;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1")
public class WalletController {

    private final WalletService walletService;
    private final TransactionService transactionService;

    @GetMapping("/wallets/{uuid}")
    public ResponseEntity<WalletDTO> findById(@PathVariable("uuid") UUID uuid) {
        Wallet wallet = walletService.findById(uuid);
        return new ResponseEntity<>(
                WalletMapper.convertWalletDtoFromWallet(wallet),
                HttpStatus.OK);
    }

    @PostMapping("/wallet")
    public ResponseEntity<NewBalanceResponse> executeTransaction(@RequestBody @Valid TransactionDTO transactionDTO,
                                                                 BindingResult bindingResult) {


        handlerBindingResult(bindingResult);
        BigDecimal newBalance = transactionService.executeTransaction(transactionDTO);

        return ResponseEntity.ok(
                NewBalanceResponse.builder()
                        .status(HttpStatus.OK)
                        .newBalance(newBalance)
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }


    private void handlerBindingResult(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder errorsMessage = new StringBuilder();

            bindingResult.getFieldErrors().forEach(fieldError -> errorsMessage
                    .append(fieldError.getField())
                    .append(" - ")
                    .append(fieldError.getDefaultMessage())
                    .append("; "));

            throw new TransactionException(errorsMessage.toString());
        }
    }
}
