package com.example.finalse.controllers;

import com.example.finalse.dtos.PaymentsRequestDto;
import com.example.finalse.dtos.PaymentsResponseDto;
import com.example.finalse.services.PaymentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentsController {
    private final PaymentsService paymentsService;

    @GetMapping("/")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public List<PaymentsResponseDto> getAll() {
        return paymentsService.getAllPayments();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER', 'USER')")
    public PaymentsResponseDto getById(@PathVariable Long id) {
        return paymentsService.getPayment(id);
    }

    @PostMapping("/")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public PaymentsResponseDto create(@RequestBody PaymentsRequestDto dto) {
        return paymentsService.addPayment(dto);
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public PaymentsResponseDto update(@PathVariable Long id, @RequestBody PaymentsRequestDto dto) {
        return paymentsService.updatePayment(id, dto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(@PathVariable Long id) {
        paymentsService.deletePayment(id);
    }
}
