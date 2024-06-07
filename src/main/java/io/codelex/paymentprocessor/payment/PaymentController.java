package io.codelex.paymentprocessor.payment;


import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
public class PaymentController {
    private final PaymentService paymentService;
    private final CSVPaymentService csvPaymentService;

    public PaymentController(PaymentService paymentService, CSVPaymentService csvPaymentService) {
        this.paymentService = paymentService;
        this.csvPaymentService = csvPaymentService;
    }

    @PostMapping("/payments")
    @ResponseStatus(HttpStatus.CREATED)
    public void createPayment(@RequestBody @Valid Payment payment) {
        paymentService.savePayment(payment);
    }

    @PostMapping("payment-files")
    @ResponseStatus(HttpStatus.CREATED)
    public void createCSVPayment(@RequestParam("file") MultipartFile file) {
        csvPaymentService.processCsv(file);
    }

    @GetMapping("/payments")
    public List<Payment> getPayments() {
        return paymentService.getPayments();
    }


}
