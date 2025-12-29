package com.example.finalse.mappers;

import com.example.finalse.dtos.ChatRequestDto;
import com.example.finalse.dtos.ChatResponseDto;
import com.example.finalse.entities.Chat;
import com.example.finalse.entities.Dialog;
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
public class ChatMapperImpl implements ChatMapper {

    @Override
    public ChatResponseDto toDto(Chat chat) {
        if ( chat == null ) {
            return null;
        }

        ChatResponseDto.ChatResponseDtoBuilder chatResponseDto = ChatResponseDto.builder();

        chatResponseDto.dialog_id( chatDialogId( chat ) );
        chatResponseDto.id( chat.getId() );
        chatResponseDto.status( chat.getStatus() );

        return chatResponseDto.build();
    }

    @Override
    public Chat toEntity(ChatRequestDto chatRequestDto) {
        if ( chatRequestDto == null ) {
            return null;
        }

        Chat.ChatBuilder chat = Chat.builder();

        chat.dialog( chatRequestDtoToDialog( chatRequestDto ) );
        chat.status( chatRequestDto.getStatus() );

        return chat.build();
    }

    @Override
    public List<ChatResponseDto> toDtoList(List<Chat> chatList) {
        if ( chatList == null ) {
            return null;
        }

        List<ChatResponseDto> list = new ArrayList<ChatResponseDto>( chatList.size() );
        for ( Chat chat : chatList ) {
            list.add( toDto( chat ) );
        }

        return list;
    }

    private Long chatDialogId(Chat chat) {
        if ( chat == null ) {
            return null;
        }
        Dialog dialog = chat.getDialog();
        if ( dialog == null ) {
            return null;
        }
        Long id = dialog.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    protected Dialog chatRequestDtoToDialog(ChatRequestDto chatRequestDto) {
        if ( chatRequestDto == null ) {
            return null;
        }

        Dialog.DialogBuilder dialog = Dialog.builder();

        dialog.id( chatRequestDto.getDialog_id() );

        return dialog.build();
    }
}
