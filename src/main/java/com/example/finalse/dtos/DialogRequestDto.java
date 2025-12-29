package com.example.finalse.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DialogRequestDto {
    private Long chat_id;
    private Long message_from_id;
    private String message;
    private String datetime;
}
