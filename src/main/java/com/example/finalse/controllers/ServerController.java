package com.example.finalse.controllers;

import com.example.finalse.dtos.ServerRequestDto;
import com.example.finalse.dtos.ServerResponseDto;
import com.example.finalse.services.ServerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/servers")
@RequiredArgsConstructor
public class ServerController {
    private final ServerService serverService;

    @GetMapping("/")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER', 'USER')")
    public List<ServerResponseDto> getAll() {
        return serverService.getAllServers();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER', 'USER')")
    public ServerResponseDto getById(@PathVariable Long id) {
        return serverService.getServer(id);
    }

    @PostMapping("/")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ServerResponseDto create(@RequestBody ServerRequestDto dto) {
        return serverService.addServer(dto);
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ServerResponseDto update(@PathVariable Long id, @RequestBody ServerRequestDto dto) {
        return serverService.updateServer(id, dto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(@PathVariable Long id) {
        serverService.deleteServer(id);
    }
}
