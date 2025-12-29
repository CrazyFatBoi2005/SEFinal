package com.example.finalse.services;

import com.example.finalse.dtos.DialogRequestDto;
import com.example.finalse.dtos.DialogResponseDto;
import com.example.finalse.entities.Dialog;
import com.example.finalse.mappers.DialogMapper;
import com.example.finalse.repositories.DialogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DialogService {

    private final DialogRepository dialogRepository;
    private final DialogMapper dialogMapper;

    public List<DialogResponseDto> getAllDialogs() {
        return dialogMapper.toDtoList(dialogRepository.findAll());
    }

    public DialogResponseDto getDialog(Long id) {
        return dialogRepository.findById(id)
                .map(dialogMapper::toDto)
                .orElse(null);
    }

    public DialogResponseDto addDialog(DialogRequestDto dto) {
        Dialog dialog = dialogMapper.toEntity(dto);
        return dialogMapper.toDto(dialogRepository.save(dialog));
    }

    public DialogResponseDto updateDialog(Long id, DialogRequestDto dto) {
        return dialogRepository.findById(id).map(existing -> {
            Dialog updated = dialogMapper.toEntity(dto);
            updated.setId(id);
            return dialogMapper.toDto(dialogRepository.save(updated));
        }).orElse(null);
    }

    public void deleteDialog(Long id) {
        dialogRepository.deleteById(id);
    }
}
