package com.sanchi.service;

import com.sanchi.model.User;
import com.sanchi.model.Withdrawal;

import java.util.List;

public interface WithdrawalService {
    Withdrawal requestWithdrawal(Long amount, User user);

    Withdrawal procedWithWithdrawal(Long withdrawalId, boolean accept) throws Exception;

    List<Withdrawal> getUsersWitdrawalHistory(User user);

    List<Withdrawal> getAllWithdrawalRequest();

}
