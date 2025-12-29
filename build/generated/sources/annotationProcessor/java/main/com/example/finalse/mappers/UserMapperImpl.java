package com.example.finalse.mappers;

import com.example.finalse.dtos.UserRequestDto;
import com.example.finalse.dtos.UserResponseDto;
import com.example.finalse.entities.Chat;
import com.example.finalse.entities.Permission;
import com.example.finalse.entities.User;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-12-29T02:08:42+0500",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-9.2.1.jar, environment: Java 21.0.8 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public UserResponseDto toDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserResponseDto.UserResponseDtoBuilder userResponseDto = UserResponseDto.builder();

        userResponseDto.chat_id( userChatId( user ) );
        userResponseDto.id( user.getId() );
        userResponseDto.email( user.getEmail() );
        userResponseDto.permissions( permissionListToStringList( user.getPermissions() ) );

        return userResponseDto.build();
    }

    @Override
    public User toEntity(UserRequestDto dto) {
        if ( dto == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.chat( userRequestDtoToChat( dto ) );
        user.email( dto.getEmail() );
        user.password( dto.getPassword() );

        return user.build();
    }

    @Override
    public List<UserResponseDto> toDtoList(List<User> userList) {
        if ( userList == null ) {
            return null;
        }

        List<UserResponseDto> list = new ArrayList<UserResponseDto>( userList.size() );
        for ( User user : userList ) {
            list.add( toDto( user ) );
        }

        return list;
    }

    private Long userChatId(User user) {
        if ( user == null ) {
            return null;
        }
        Chat chat = user.getChat();
        if ( chat == null ) {
            return null;
        }
        Long id = chat.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    protected List<String> permissionListToStringList(List<Permission> list) {
        if ( list == null ) {
            return null;
        }

        List<String> list1 = new ArrayList<String>( list.size() );
        for ( Permission permission : list ) {
            list1.add( permissionMapper.toString( permission ) );
        }

        return list1;
    }

    protected Chat userRequestDtoToChat(UserRequestDto userRequestDto) {
        if ( userRequestDto == null ) {
            return null;
        }

        Chat.ChatBuilder chat = Chat.builder();

        chat.id( userRequestDto.getChat_id() );

        return chat.build();
    }
}
