package io.codelex.paymentprocessor.payment;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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

    @Override
    public String getDebtorLocation(String ipAddress) {
        String apiUrl = "https://ipapi.co/" + ipAddress + "/json/";
        try {
            RestTemplate restTemplate = new RestTemplate();
            String response = restTemplate.getForObject(apiUrl, String.class);
            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNode = mapper.readTree(response);
            return jsonNode.get("country_name").asText();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}
