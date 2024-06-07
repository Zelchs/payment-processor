package io.codelex.paymentprocessor.payment;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

@Service
public class CSVPaymentService {

    private final PaymentService paymentService;

    public CSVPaymentService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }


    public void processCsv(MultipartFile file) throws RuntimeException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            CSVReader csvReader = new CSVReader(br);

            CsvToBean<Payment> csvToBean = new CsvToBeanBuilder<Payment>(csvReader)
                    .withType(Payment.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
            @Valid
            List<Payment> payments = csvToBean.parse();

            for (Payment payment : payments) {
                validateAndSavePayment(payment);
            }

        } catch (IOException e) {
            throw new RuntimeException("CSV File Not Found");
        }
    }

    private void validateAndSavePayment(Payment payment) {
        if (isValidPayment(payment)) {
            paymentService.savePayment(payment);
        } else {
            throw new IllegalArgumentException("Invalid payment data in CSV: " + payment);
        }
    }

    private boolean isValidPayment(Payment payment) {
        return payment.isValidDebtorIban() && payment.isValidAmount() && payment.isValidCountry();
    }
}
