package io.codelex.paymentprocessor.payment;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PaymentService {
    void savePayment(Payment payment);

    List<Payment> getPayments();
}
