package com.example.finalse.mappers;

import com.example.finalse.dtos.DialogRequestDto;
import com.example.finalse.dtos.DialogResponseDto;
import com.example.finalse.entities.Dialog;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DialogMapper {
    @Mapping(source = "chat.id", target = "chat_id")
    @Mapping(source = "message_from.id", target = "message_from_id")
    DialogResponseDto toDto(Dialog dialog);

    @Mapping(source = "chat_id", target = "chat.id")
    @Mapping(source = "message_from_id", target = "message_from.id")
    Dialog toEntity(DialogRequestDto dialogRequestDto);

    @Mapping(source = "chat.id", target = "chat_id")
    @Mapping(source = "message_from.id", target = "message_from_id")
    List<DialogResponseDto> toDtoList(List<Dialog> dialogList);
}