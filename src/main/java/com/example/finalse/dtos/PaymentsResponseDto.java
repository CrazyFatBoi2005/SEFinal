package com.example.finalse.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentsResponseDto {
    private Long id;
    private Long user_id;
    private String date;
    private Double value;
    private String status;
}
