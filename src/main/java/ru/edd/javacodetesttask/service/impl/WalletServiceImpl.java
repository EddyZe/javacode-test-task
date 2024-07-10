package ru.edd.javacodetesttask.service.impl;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.edd.javacodetesttask.entity.Wallet;
import ru.edd.javacodetesttask.exception.WalletNotFoundException;
import ru.edd.javacodetesttask.repository.WalletRepository;
import ru.edd.javacodetesttask.service.WalletService;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class WalletServiceImpl  implements WalletService {

    private final WalletRepository walletRepository;


    @Override
    public Wallet findById(UUID uuid) {
        return walletRepository.findById(uuid)
                .orElseThrow(() -> new WalletNotFoundException("Кошелек с таким ID не найден!"));
    }

    @Override
    public void save(Wallet wallet) {
        walletRepository.save(wallet);
    }

}
