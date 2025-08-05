package com.sanchi.service;

import com.sanchi.model.Order;
import com.sanchi.model.User;
import com.sanchi.model.Wallet;

public interface  WalletService {
    Wallet getUserWallet(User user);
    Wallet addBalance(Wallet wallet, Long money);

    Wallet findWalletById(Long id) throws Exception;
    Wallet walletToWalletTransfer(User sender,Wallet receiverWallet, Long amount ) throws Exception;
    Wallet payOrderPayment(Order order, User user) throws Exception;
}
