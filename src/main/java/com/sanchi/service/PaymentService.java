package com.sanchi.service;

import com.sanchi.domain.PaymentMethod;
import com.sanchi.model.PaymentOrder;
import com.sanchi.model.User;
import com.sanchi.response.PaymentResponse;

public interface PaymentService {

    PaymentOrder createOrder(User user, Long amount, PaymentMethod paymentMethod);

    PaymentOrder getPaymentOrderById(Long id) throws Exception;

    Boolean proceedPaymentOrder(PaymentOrder paymentOrder, String paymentId) throws Exception;

    PaymentResponse createRazorpayPaymentLink(User user, Long amount, Long orderId) throws Exception;

    PaymentResponse createStripePaymentLink(User user, Long amount, Long orderId);
}
