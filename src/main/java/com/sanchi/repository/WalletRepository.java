package com.sanchi.repository;

import com.sanchi.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepository extends JpaRepository<Wallet,Long> {
    Wallet findByUserId(Long userId);

}
