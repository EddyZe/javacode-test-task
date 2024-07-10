package ru.edd.javacodetesttask.service;

import ru.edd.javacodetesttask.entity.Wallet;

import java.util.UUID;

public interface WalletService {
    Wallet findById(UUID uuid);

    void save(Wallet wallet);
}
