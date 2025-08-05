package com.sanchi.service;

import com.sanchi.domain.WalletTransactionType;
import com.sanchi.model.Wallet;
import com.sanchi.model.WalletTransaction;
import com.sanchi.repository.WalletTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class WalletTransactionServiceImpl implements WalletTransactionService {

    @Autowired
    private WalletTransactionRepository transactionRepository;

    @Override
    public WalletTransaction createTransaction(Wallet wallet, WalletTransactionType type, String transferId, String purpose, Long amount) {
        WalletTransaction transaction = new WalletTransaction();
        transaction.setWallet(wallet);
        transaction.setType(type);
        transaction.setDate(LocalDate.now());
        transaction.setTransferId(transferId);
        transaction.setPurpose(purpose);
        transaction.setAmount(amount);
        return transactionRepository.save(transaction);
    }

    @Override
    public List<WalletTransaction> getTransactionsForWallet(Wallet wallet) {
        return transactionRepository.findByWallet(wallet);
    }
}
