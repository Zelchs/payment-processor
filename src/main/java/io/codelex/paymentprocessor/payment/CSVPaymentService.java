package io.codelex.paymentprocessor.payment;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

@Service
public class CSVPaymentService {

    private final PaymentService paymentService;

    public CSVPaymentService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }


    public void processCsv(MultipartFile file) throws FileNotFoundException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            CSVReader csvReader = new CSVReader(br);

            CsvToBean<Payment> csvToBean = new CsvToBeanBuilder<Payment>(csvReader)
                    .withType(Payment.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            List<Payment> payments = csvToBean.parse();

            payments.forEach(paymentService::savePayment);

        } catch (IOException e) {
            throw new FileNotFoundException("CSV File Not Found");
        }
    }
}
