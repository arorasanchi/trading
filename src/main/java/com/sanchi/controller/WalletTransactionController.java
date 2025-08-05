package com.sanchi.controller;

import com.sanchi.model.User;
import com.sanchi.model.Wallet;
import com.sanchi.model.WalletTransaction;
import com.sanchi.service.UserService;
import com.sanchi.service.WalletService;
import com.sanchi.service.WalletTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class WalletTransactionController {

    @Autowired
    private WalletTransactionService transactionService;

    @Autowired
    private UserService userService;

    @Autowired
    private WalletService walletService;

    @GetMapping
    public ResponseEntity<List<WalletTransaction>> getUserTransactions(@RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserProfileByJwt(jwt);
        Wallet wallet = walletService.getUserWallet(user);
        List<WalletTransaction> transactions = transactionService.getTransactionsForWallet(wallet);
        return ResponseEntity.ok(transactions);
    }
}
