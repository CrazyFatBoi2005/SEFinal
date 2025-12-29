package com.example.finalse.mappers;

import com.example.finalse.dtos.PaymentsRequestDto;
import com.example.finalse.dtos.PaymentsResponseDto;
import com.example.finalse.entities.Payments;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PaymentsMapper {
    @Mapping(source = "user.id", target = "user_id")
    PaymentsResponseDto toDto(Payments payments);

    @Mapping(source = "user_id", target = "user.id")
    Payments toEntity(PaymentsRequestDto paymentsRequestDto);

    @Mapping(source = "user.id", target = "user_id")
    List<PaymentsResponseDto> toDtoList(List<Payments> paymentsList);
}