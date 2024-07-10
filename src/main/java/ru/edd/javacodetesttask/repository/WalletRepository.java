package ru.edd.javacodetesttask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.edd.javacodetesttask.entity.Wallet;

import java.util.UUID;


@Repository
public interface WalletRepository extends JpaRepository<Wallet, UUID> {
}
