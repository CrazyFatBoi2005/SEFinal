package com.example.finalse.mappers;

import com.example.finalse.dtos.DialogRequestDto;
import com.example.finalse.dtos.DialogResponseDto;
import com.example.finalse.entities.Chat;
import com.example.finalse.entities.Dialog;
import com.example.finalse.entities.User;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-12-29T02:08:42+0500",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-9.2.1.jar, environment: Java 21.0.8 (Oracle Corporation)"
)
@Component
public class DialogMapperImpl implements DialogMapper {

    @Override
    public DialogResponseDto toDto(Dialog dialog) {
        if ( dialog == null ) {
            return null;
        }

        DialogResponseDto.DialogResponseDtoBuilder dialogResponseDto = DialogResponseDto.builder();

        dialogResponseDto.chat_id( dialogChatId( dialog ) );
        dialogResponseDto.message_from_id( dialogMessage_fromId( dialog ) );
        dialogResponseDto.id( dialog.getId() );
        dialogResponseDto.message( dialog.getMessage() );
        dialogResponseDto.datetime( dialog.getDatetime() );

        return dialogResponseDto.build();
    }

    @Override
    public Dialog toEntity(DialogRequestDto dialogRequestDto) {
        if ( dialogRequestDto == null ) {
            return null;
        }

        Dialog.DialogBuilder dialog = Dialog.builder();

        dialog.chat( dialogRequestDtoToChat( dialogRequestDto ) );
        dialog.message_from( dialogRequestDtoToUser( dialogRequestDto ) );
        dialog.message( dialogRequestDto.getMessage() );
        dialog.datetime( dialogRequestDto.getDatetime() );

        return dialog.build();
    }

    @Override
    public List<DialogResponseDto> toDtoList(List<Dialog> dialogList) {
        if ( dialogList == null ) {
            return null;
        }

        List<DialogResponseDto> list = new ArrayList<DialogResponseDto>( dialogList.size() );
        for ( Dialog dialog : dialogList ) {
            list.add( toDto( dialog ) );
        }

        return list;
    }

    private Long dialogChatId(Dialog dialog) {
        if ( dialog == null ) {
            return null;
        }
        Chat chat = dialog.getChat();
        if ( chat == null ) {
            return null;
        }
        Long id = chat.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long dialogMessage_fromId(Dialog dialog) {
        if ( dialog == null ) {
            return null;
        }
        User message_from = dialog.getMessage_from();
        if ( message_from == null ) {
            return null;
        }
        Long id = message_from.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    protected Chat dialogRequestDtoToChat(DialogRequestDto dialogRequestDto) {
        if ( dialogRequestDto == null ) {
            return null;
        }

        Chat.ChatBuilder chat = Chat.builder();

        chat.id( dialogRequestDto.getChat_id() );

        return chat.build();
    }

    protected User dialogRequestDtoToUser(DialogRequestDto dialogRequestDto) {
        if ( dialogRequestDto == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.id( dialogRequestDto.getMessage_from_id() );

        return user.build();
    }
}
