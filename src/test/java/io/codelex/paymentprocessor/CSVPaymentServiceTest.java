package io.codelex.paymentprocessor;

import io.codelex.paymentprocessor.payment.CSVPaymentService;
import io.codelex.paymentprocessor.payment.Payment;
import io.codelex.paymentprocessor.payment.PaymentService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
 class CSVPaymentServiceTest {
    @Mock
    PaymentService paymentService;

    @InjectMocks
    CSVPaymentService csvPaymentService;

    @Test
    void testProcessCSV() throws IOException {
        String csvContent = "amount,debtorIban\n15.0,LT356437978869712537\n-20.0,LT356437978869712537\n20.0,LT356437978869712535";
        MockMultipartFile file = new MockMultipartFile("file", "payments.csv", "text/csv", new ByteArrayInputStream(csvContent.getBytes()));

        assertThrows(IllegalArgumentException.class, () -> csvPaymentService.processCsv(file));

        verify(paymentService, times(1)).savePayment(any(Payment.class));
    }

}
