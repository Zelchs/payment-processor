package io.codelex.paymentprocessor.payment;


import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PaymentController {
    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/payments")
    @ResponseStatus(HttpStatus.CREATED)
    public void createPayment(@RequestBody @Valid Payment payment) {
        paymentService.savePayment(payment);
    }

    @GetMapping("/payments")
    public List<Payment> getPayments() {
        return paymentService.getPayments();
    }


}
