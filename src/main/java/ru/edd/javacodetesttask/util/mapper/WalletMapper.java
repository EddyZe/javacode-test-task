package ru.edd.javacodetesttask.util.mapper;

import ru.edd.javacodetesttask.dto.WalletDTO;
import ru.edd.javacodetesttask.entity.Wallet;

public class WalletMapper {

    public static WalletDTO convertWalletDtoFromWallet(Wallet wallet) {
        return WalletDTO.builder()
                .balance(wallet.getBalance())
                .walletId(wallet.getId())
                .build();
    }
}
