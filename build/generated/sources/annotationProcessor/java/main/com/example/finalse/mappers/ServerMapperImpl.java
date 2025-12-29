package com.example.finalse.mappers;

import com.example.finalse.dtos.ServerRequestDto;
import com.example.finalse.dtos.ServerResponseDto;
import com.example.finalse.entities.Server;
import com.example.finalse.entities.User;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-12-29T02:08:43+0500",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-9.2.1.jar, environment: Java 21.0.8 (Oracle Corporation)"
)
@Component
public class ServerMapperImpl implements ServerMapper {

    @Override
    public ServerResponseDto toDto(Server server) {
        if ( server == null ) {
            return null;
        }

        ServerResponseDto.ServerResponseDtoBuilder serverResponseDto = ServerResponseDto.builder();

        serverResponseDto.user_id( serverUserId( server ) );
        serverResponseDto.id( server.getId() );
        serverResponseDto.ssh( server.getSsh() );

        return serverResponseDto.build();
    }

    @Override
    public Server toEntity(ServerRequestDto serverRequestDto) {
        if ( serverRequestDto == null ) {
            return null;
        }

        Server.ServerBuilder server = Server.builder();

        server.user( serverRequestDtoToUser( serverRequestDto ) );
        server.ssh( serverRequestDto.getSsh() );

        return server.build();
    }

    @Override
    public List<ServerResponseDto> toDtoList(List<Server> serverList) {
        if ( serverList == null ) {
            return null;
        }

        List<ServerResponseDto> list = new ArrayList<ServerResponseDto>( serverList.size() );
        for ( Server server : serverList ) {
            list.add( toDto( server ) );
        }

        return list;
    }

    private Long serverUserId(Server server) {
        if ( server == null ) {
            return null;
        }
        User user = server.getUser();
        if ( user == null ) {
            return null;
        }
        Long id = user.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    protected User serverRequestDtoToUser(ServerRequestDto serverRequestDto) {
        if ( serverRequestDto == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.id( serverRequestDto.getUser_id() );

        return user.build();
    }
}
