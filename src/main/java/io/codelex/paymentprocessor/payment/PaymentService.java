package io.codelex.paymentprocessor.payment;

import java.util.List;

public interface PaymentService {
    void savePayment(Payment payment);

    List<Payment> getPayments();

    List<Payment> getPaymentsByDebtorIBAN(String iban);
}
