package com.sanchi.service;

import com.sanchi.model.PaymentDetails;
import com.sanchi.model.User;

public interface PaymentDetailsService   {
    public PaymentDetails addPaymentDetails(String accountNumber,
                                            String accountHolderName,
                                            String ifsc,
                                            String bankName,
                                            User user

    );
    public PaymentDetails getUsersPaymentDetails(User user);
}
