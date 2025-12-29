package com.example.finalse.services;

import com.example.finalse.dtos.ChatRequestDto;
import com.example.finalse.dtos.ChatResponseDto;
import com.example.finalse.entities.Chat;
import com.example.finalse.mappers.ChatMapper;
import com.example.finalse.repositories.ChatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final ChatRepository chatRepository;
    private final ChatMapper chatMapper;

    public List<ChatResponseDto> getAllChats() {
        return chatMapper.toDtoList(chatRepository.findAll());
    }

    public ChatResponseDto getChat(Long id) {
        return chatRepository.findById(id)
                .map(chatMapper::toDto)
                .orElse(null);
    }

    public ChatResponseDto addChat(ChatRequestDto dto) {
        Chat chat = chatMapper.toEntity(dto);
        return chatMapper.toDto(chatRepository.save(chat));
    }

    public ChatResponseDto updateChat(Long id, ChatRequestDto dto) {
        return chatRepository.findById(id).map(existing -> {
            Chat updated = chatMapper.toEntity(dto);
            updated.setId(id);
            return chatMapper.toDto(chatRepository.save(updated));
        }).orElse(null);
    }

    public void deleteChat(Long id) {
        chatRepository.deleteById(id);
    }
}
