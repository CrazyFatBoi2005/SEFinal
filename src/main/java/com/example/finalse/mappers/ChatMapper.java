package com.example.finalse.mappers;

import com.example.finalse.dtos.ChatRequestDto;
import com.example.finalse.dtos.ChatResponseDto;
import com.example.finalse.entities.Chat;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ChatMapper {
    @Mapping(source = "dialog.id", target = "dialog_id")
    ChatResponseDto toDto(Chat chat);

    @Mapping(source = "dialog_id", target = "dialog.id")
    Chat toEntity(ChatRequestDto chatRequestDto);

    @Mapping(source = "dialog.id", target = "dialog_id")
    List<ChatResponseDto> toDtoList(List<Chat> chatList);
}