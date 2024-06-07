package io.codelex.paymentprocessor.payment;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    public PaymentServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public void savePayment(Payment payment) {
        paymentRepository.save(payment);
    }

    @Override
    public List<Payment> getPayments() {
        return paymentRepository.findAll();
    }

    @Override
    public List<Payment> getPaymentsByDebtorIBAN(String iban) {
        return paymentRepository.findAllByDebtorIban(iban);
    }
}
