package com.example.finalse.mappers;

import com.example.finalse.dtos.ServerRequestDto;
import com.example.finalse.dtos.ServerResponseDto;
import com.example.finalse.entities.Server;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ServerMapper {
    @Mapping(source = "user.id", target = "user_id")
    ServerResponseDto toDto(Server server);

    @Mapping(source = "user_id", target = "user.id")
    Server toEntity(ServerRequestDto serverRequestDto);

    @Mapping(source = "user.id", target = "user_id")
    List<ServerResponseDto> toDtoList(List<Server> serverList);
}
