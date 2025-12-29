package com.example.finalse.controllers;

import com.example.finalse.dtos.DialogRequestDto;
import com.example.finalse.dtos.DialogResponseDto;
import com.example.finalse.services.DialogService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dialogs")
@RequiredArgsConstructor
public class DialogController {
    private final DialogService dialogService;

    @GetMapping("/")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER', 'USER')")
    public List<DialogResponseDto> getAll() {
        return dialogService.getAllDialogs();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER', 'USER')")
    public DialogResponseDto getById(@PathVariable Long id) {
        return dialogService.getDialog(id);
    }

    @PostMapping("/")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER', 'USER')")
    public DialogResponseDto create(@RequestBody DialogRequestDto dto) {
        return dialogService.addDialog(dto);
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER','ADMIN', 'MANAGER')")
    public DialogResponseDto update(@PathVariable Long id, @RequestBody DialogRequestDto dto) {
        return dialogService.updateDialog(id, dto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(@PathVariable Long id) {
        dialogService.deleteDialog(id);
    }
}
