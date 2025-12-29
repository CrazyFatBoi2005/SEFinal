package com.example.finalse.controllers;

import com.example.finalse.dtos.ChatRequestDto;
import com.example.finalse.dtos.ChatResponseDto;
import com.example.finalse.services.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chats")
@RequiredArgsConstructor
public class ChatController {
    private final ChatService chatService;

    @GetMapping("/")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER', 'USER')")
    public List<ChatResponseDto> getAll() {
        return chatService.getAllChats();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER', 'USER')")
    public ChatResponseDto getById(@PathVariable Long id) {
        return chatService.getChat(id);
    }

    @PostMapping("/")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER', 'USER')")
    public ChatResponseDto create(@RequestBody ChatRequestDto dto) {
        return chatService.addChat(dto);
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ChatResponseDto update(@PathVariable Long id, @RequestBody ChatRequestDto dto) {
        return chatService.updateChat(id, dto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(@PathVariable Long id) {
        chatService.deleteChat(id);
    }
}
