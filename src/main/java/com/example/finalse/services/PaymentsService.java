package com.example.finalse.services;

import com.example.finalse.dtos.PaymentsRequestDto;
import com.example.finalse.dtos.PaymentsResponseDto;
import com.example.finalse.entities.Payments;
import com.example.finalse.mappers.PaymentsMapper;
import com.example.finalse.repositories.PaymentsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentsService {

    private final PaymentsRepository paymentsRepository;
    private final PaymentsMapper paymentsMapper;

    public List<PaymentsResponseDto> getAllPayments() {
        return paymentsMapper.toDtoList(paymentsRepository.findAll());
    }

    public PaymentsResponseDto getPayment(Long id) {
        return paymentsRepository.findById(id)
                .map(paymentsMapper::toDto)
                .orElse(null);
    }

    public PaymentsResponseDto addPayment(PaymentsRequestDto dto) {
        Payments payment = paymentsMapper.toEntity(dto);
        return paymentsMapper.toDto(paymentsRepository.save(payment));
    }

    public PaymentsResponseDto updatePayment(Long id, PaymentsRequestDto dto) {
        return paymentsRepository.findById(id).map(existing -> {
            Payments updated = paymentsMapper.toEntity(dto);
            updated.setId(id);
            return paymentsMapper.toDto(paymentsRepository.save(updated));
        }).orElse(null);
    }

    public void deletePayment(Long id) {
        paymentsRepository.deleteById(id);
    }
}
