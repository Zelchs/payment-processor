package io.codelex.paymentprocessor.payment;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.AssertTrue;
import org.apache.commons.validator.routines.IBANValidator;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
public class Payment {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    @Column
    private BigDecimal amount;
    @Column
    String debtorIban;
    private LocalDateTime creationTime;


    public Payment() {
        //JPA default
    }

    public Payment(BigDecimal amount, String debtorIban) {
        this.amount = amount;
        this.debtorIban = debtorIban;
        this.creationTime = LocalDateTime.now();
    }

    @AssertTrue
    public boolean isValidDebtorIban() {
        IBANValidator validator = IBANValidator.getInstance();
        return validator.isValid(debtorIban);
    }

    @AssertTrue
    public boolean isValidAmount() {
        return amount.compareTo(BigDecimal.ZERO) > 0;
    }

    @AssertTrue
    public boolean isValidCountry() {
        return debtorIban.startsWith("LV") || debtorIban.startsWith("EE") || debtorIban.startsWith("LT");
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getDebtorIban() {
        return debtorIban;
    }

    public void setDebtorIban(String debtorIban) {
        this.debtorIban = debtorIban;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        return Objects.equals(id, payment.id) && Objects.equals(amount, payment.amount) && Objects.equals(debtorIban, payment.debtorIban) && Objects.equals(creationTime, payment.creationTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, amount, debtorIban, creationTime);
    }
}
