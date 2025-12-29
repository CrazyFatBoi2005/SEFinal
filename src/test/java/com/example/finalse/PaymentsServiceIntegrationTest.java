package com.example.finalse;

import com.example.finalse.dtos.PaymentsRequestDto;
import com.example.finalse.dtos.PaymentsResponseDto;
import com.example.finalse.services.PaymentsService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@SpringBootTest
@ActiveProfiles("test")
public class PaymentsServiceIntegrationTest {

    @Autowired
    private PaymentsService paymentsService;

    @Test
    public void getAllPayments() {
        List<PaymentsResponseDto> paymentsList = paymentsService.getAllPayments();
        Assertions.assertNotNull(paymentsList);
    }

    @Test
    public void createUpdateDeletePayments() {
        PaymentsRequestDto newPayment = PaymentsRequestDto.builder()
                .date("2024-01-15")
                .value(150.0)
                .status("PENDING")
                .build();

        PaymentsResponseDto createdPayment = paymentsService.addPayment(newPayment);

        Assertions.assertNotNull(createdPayment);
        Assertions.assertNotNull(createdPayment.getId());
        Assertions.assertEquals(newPayment.getDate(), createdPayment.getDate());
        Assertions.assertEquals(newPayment.getValue(), createdPayment.getValue());
        Assertions.assertEquals(newPayment.getStatus(), createdPayment.getStatus());

        PaymentsRequestDto updateDto = PaymentsRequestDto.builder()
                .date("2024-02-20")
                .value(300.0)
                .status("PAID")
                .build();

        PaymentsResponseDto updatedPayment = paymentsService.updatePayment(createdPayment.getId(), updateDto);

        Assertions.assertNotNull(updatedPayment);
        Assertions.assertEquals(createdPayment.getId(), updatedPayment.getId());
        Assertions.assertEquals("PAID", updatedPayment.getStatus());

        paymentsService.deletePayment(updatedPayment.getId());

        PaymentsResponseDto deletedPayment = paymentsService.getPayment(updatedPayment.getId());
        Assertions.assertNull(deletedPayment);
    }

    @Test
    public void getPaymentById() {
        PaymentsRequestDto newPayment = PaymentsRequestDto.builder()
                .date("2024-03-10")
                .value(99.99)
                .status("NEW")
                .build();

        PaymentsResponseDto createdPayment = paymentsService.addPayment(newPayment);
        Assertions.assertNotNull(createdPayment);

        PaymentsResponseDto foundPayment = paymentsService.getPayment(createdPayment.getId());

        Assertions.assertNotNull(foundPayment);
        Assertions.assertEquals(createdPayment.getId(), foundPayment.getId());

        paymentsService.deletePayment(createdPayment.getId());
    }
}
