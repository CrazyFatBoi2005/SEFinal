package com.example.finalse.mappers;

import com.example.finalse.dtos.UserRequestDto;
import com.example.finalse.dtos.UserResponseDto;
import com.example.finalse.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = PermissionMapper.class)
public interface UserMapper {

    @Mapping(source = "chat.id", target = "chat_id")
    UserResponseDto toDto(User user);

    @Mapping(source = "chat_id", target = "chat.id")
    User toEntity(UserRequestDto dto);

    @Mapping(source = "chat.id", target = "chat_id")
    List<UserResponseDto> toDtoList(List<User> userList);
}