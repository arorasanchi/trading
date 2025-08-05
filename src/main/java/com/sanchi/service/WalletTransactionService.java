package com.sanchi.service;

import com.sanchi.model.Wallet;
import com.sanchi.model.WalletTransaction;
import com.sanchi.domain.WalletTransactionType;

import java.util.List;

public interface WalletTransactionService {
    WalletTransaction createTransaction(Wallet wallet, WalletTransactionType type, String transferId, String purpose, Long amount);
    List<WalletTransaction> getTransactionsForWallet(Wallet wallet);
}
