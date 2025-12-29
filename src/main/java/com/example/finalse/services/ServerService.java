package com.example.finalse.services;

import com.example.finalse.dtos.ServerRequestDto;
import com.example.finalse.dtos.ServerResponseDto;
import com.example.finalse.entities.Server;
import com.example.finalse.mappers.ServerMapper;
import com.example.finalse.repositories.ServerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServerService {

    private final ServerRepository serverRepository;
    private final ServerMapper serverMapper;

    public List<ServerResponseDto> getAllServers() {
        return serverMapper.toDtoList(serverRepository.findAll());
    }

    public ServerResponseDto getServer(Long id) {
        return serverRepository.findById(id)
                .map(serverMapper::toDto)
                .orElse(null);
    }

    public ServerResponseDto addServer(ServerRequestDto dto) {
        Server server = serverMapper.toEntity(dto);
        return serverMapper.toDto(serverRepository.save(server));
    }

    public ServerResponseDto updateServer(Long id, ServerRequestDto dto) {
        return serverRepository.findById(id).map(existing -> {
            Server updated = serverMapper.toEntity(dto);
            updated.setId(id);
            return serverMapper.toDto(serverRepository.save(updated));
        }).orElse(null);
    }

    public void deleteServer(Long id) {
        serverRepository.deleteById(id);
    }
}
