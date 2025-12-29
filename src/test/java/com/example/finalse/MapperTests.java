package com.example.finalse;

import com.example.finalse.dtos.*;
import com.example.finalse.entities.*;
import com.example.finalse.mappers.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@ActiveProfiles("test")
public class MapperTests {

    @Autowired
    private ChatMapper chatMapper;

    @Autowired
    private DialogMapper dialogMapper;

    @Autowired
    private ServerMapper serverMapper;

    @Autowired
    private PaymentsMapper paymentsMapper;

    @Autowired
    private UserMapper userMapper;

    @Test
    public void chatEntityToDto() {
        Chat chat = Chat.builder()
                .id(1L)
                .status("ACTIVE")
                .build();

        ChatResponseDto dto = chatMapper.toDto(chat);

        Assertions.assertNotNull(dto);
        Assertions.assertEquals(chat.getId(), dto.getId());
        Assertions.assertEquals(chat.getStatus(), dto.getStatus());
    }

    @Test
    public void chatDtoToEntity() {
        ChatRequestDto dto = ChatRequestDto.builder()
                .status("ACTIVE")
                .dialog_id(1L)
                .build();

        Chat chat = chatMapper.toEntity(dto);

        Assertions.assertNotNull(chat);
        Assertions.assertEquals(dto.getStatus(), chat.getStatus());
    }

    @Test
    public void chatListToDto() {
        List<Chat> chatList = new ArrayList<>();
        chatList.add(Chat.builder().id(1L).status("ACTIVE").build());
        chatList.add(Chat.builder().id(2L).status("INACTIVE").build());

        List<ChatResponseDto> dtoList = chatMapper.toDtoList(chatList);

        Assertions.assertNotNull(dtoList);
        Assertions.assertEquals(chatList.size(), dtoList.size());

        for (int i = 0; i < chatList.size(); i++) {
            Assertions.assertEquals(chatList.get(i).getId(), dtoList.get(i).getId());
            Assertions.assertEquals(chatList.get(i).getStatus(), dtoList.get(i).getStatus());
        }
    }

    @Test
    public void serverEntityToDto() {
        User user = User.builder().id(1L).email("test@test.com").build();
        Server server = Server.builder()
                .id(1L)
                .ssh("ssh-key-123")
                .user(user)
                .build();

        ServerResponseDto dto = serverMapper.toDto(server);

        Assertions.assertNotNull(dto);
        Assertions.assertEquals(server.getId(), dto.getId());
        Assertions.assertEquals(server.getSsh(), dto.getSsh());
        Assertions.assertEquals(server.getUser().getId(), dto.getUser_id());
    }

    @Test
    public void serverDtoToEntity() {
        ServerRequestDto dto = ServerRequestDto.builder()
                .ssh("ssh-key-456")
                .user_id(1L)
                .build();

        Server server = serverMapper.toEntity(dto);

        Assertions.assertNotNull(server);
        Assertions.assertEquals(dto.getSsh(), server.getSsh());
    }

    @Test
    public void paymentsEntityToDto() {
        User user = User.builder().id(1L).email("test@test.com").build();
        Payments payments = Payments.builder()
                .id(1L)
                .date("2024-01-01")
                .value(100.0)
                .status("PAID")
                .user(user)
                .build();

        PaymentsResponseDto dto = paymentsMapper.toDto(payments);

        Assertions.assertNotNull(dto);
        Assertions.assertEquals(payments.getId(), dto.getId());
        Assertions.assertEquals(payments.getDate(), dto.getDate());
        Assertions.assertEquals(payments.getValue(), dto.getValue());
        Assertions.assertEquals(payments.getStatus(), dto.getStatus());
        Assertions.assertEquals(payments.getUser().getId(), dto.getUser_id());
    }

    @Test
    public void paymentsDtoToEntity() {
        PaymentsRequestDto dto = PaymentsRequestDto.builder()
                .date("2024-01-01")
                .value(200.0)
                .status("PENDING")
                .user_id(1L)
                .build();

        Payments payments = paymentsMapper.toEntity(dto);

        Assertions.assertNotNull(payments);
        Assertions.assertEquals(dto.getDate(), payments.getDate());
        Assertions.assertEquals(dto.getValue(), payments.getValue());
        Assertions.assertEquals(dto.getStatus(), payments.getStatus());
    }

    @Test
    public void dialogEntityToDto() {
        User user = User.builder().id(1L).email("test@test.com").build();
        Chat chat = Chat.builder().id(1L).status("ACTIVE").build();
        Dialog dialog = Dialog.builder()
                .id(1L)
                .message("Hello")
                .datetime("2024-01-01 10:00")
                .message_from(user)
                .chat(chat)
                .build();

        DialogResponseDto dto = dialogMapper.toDto(dialog);

        Assertions.assertNotNull(dto);
        Assertions.assertEquals(dialog.getId(), dto.getId());
        Assertions.assertEquals(dialog.getMessage(), dto.getMessage());
        Assertions.assertEquals(dialog.getDatetime(), dto.getDatetime());
        Assertions.assertEquals(dialog.getMessage_from().getId(), dto.getMessage_from_id());
        Assertions.assertEquals(dialog.getChat().getId(), dto.getChat_id());
    }

    @Test
    public void dialogDtoToEntity() {
        DialogRequestDto dto = DialogRequestDto.builder()
                .message("Test message")
                .datetime("2024-01-01 12:00")
                .message_from_id(1L)
                .chat_id(1L)
                .build();

        Dialog dialog = dialogMapper.toEntity(dto);

        Assertions.assertNotNull(dialog);
        Assertions.assertEquals(dto.getMessage(), dialog.getMessage());
        Assertions.assertEquals(dto.getDatetime(), dialog.getDatetime());
    }
}
