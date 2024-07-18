package io.codelex.paymentprocessor.payment;


import jakarta.servlet.http.HttpServletRequest;
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
    public void createPayment(HttpServletRequest request, @RequestBody @Valid Payment payment) {
        String ipAddress = request.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
        }
        payment.setDebtorLocation(paymentService.getDebtorLocation(ipAddress));
        paymentService.savePayment(payment);
    }

    @PostMapping("payment-files")
    @ResponseStatus(HttpStatus.CREATED)
    public void uploadCSVPayment(@RequestParam("file") MultipartFile file) {
        csvPaymentService.processCsv(file);
    }

    @GetMapping("/payments")
    public List<Payment> getPayments(@RequestParam(required = false) String debtorIban) {
        return debtorIban != null ? paymentService.getPaymentsByDebtorIBAN(debtorIban) : paymentService.getPayments();
    }


}
