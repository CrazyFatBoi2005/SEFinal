package com.example.finalse;

import com.example.finalse.dtos.ChatRequestDto;
import com.example.finalse.dtos.ChatResponseDto;
import com.example.finalse.services.ChatService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@SpringBootTest
@ActiveProfiles("test")
public class ChatServiceIntegrationTest {

    @Autowired
    private ChatService chatService;

    @Test
    public void getAllChats() {
        List<ChatResponseDto> chatList = chatService.getAllChats();
        Assertions.assertNotNull(chatList);
    }

    @Test
    public void createUpdateDeleteChat() {
        ChatRequestDto newChat = ChatRequestDto.builder()
                .status("NEW")
                .build();

        ChatResponseDto createdChat = chatService.addChat(newChat);

        Assertions.assertNotNull(createdChat);
        Assertions.assertNotNull(createdChat.getId());
        Assertions.assertEquals(newChat.getStatus(), createdChat.getStatus());

        ChatRequestDto updateDto = ChatRequestDto.builder()
                .status("UPDATED")
                .build();

        ChatResponseDto updatedChat = chatService.updateChat(createdChat.getId(), updateDto);

        Assertions.assertNotNull(updatedChat);
        Assertions.assertEquals(createdChat.getId(), updatedChat.getId());
        Assertions.assertEquals("UPDATED", updatedChat.getStatus());

        chatService.deleteChat(updatedChat.getId());

        ChatResponseDto deletedChat = chatService.getChat(updatedChat.getId());
        Assertions.assertNull(deletedChat);
    }

    @Test
    public void getChatById() {
        ChatRequestDto newChat = ChatRequestDto.builder()
                .status("TEST")
                .build();

        ChatResponseDto createdChat = chatService.addChat(newChat);
        Assertions.assertNotNull(createdChat);

        ChatResponseDto foundChat = chatService.getChat(createdChat.getId());

        Assertions.assertNotNull(foundChat);
        Assertions.assertEquals(createdChat.getId(), foundChat.getId());

        chatService.deleteChat(createdChat.getId());
    }
}
