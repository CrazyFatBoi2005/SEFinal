package com.example.finalse;

import com.example.finalse.dtos.ServerRequestDto;
import com.example.finalse.dtos.ServerResponseDto;
import com.example.finalse.services.ServerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@SpringBootTest
@ActiveProfiles("test")
public class ServerServiceIntegrationTest {

    @Autowired
    private ServerService serverService;

    @Test
    public void getAllServers() {
        List<ServerResponseDto> serverList = serverService.getAllServers();
        Assertions.assertNotNull(serverList);
    }

    @Test
    public void createUpdateDeleteServer() {
        ServerRequestDto newServer = ServerRequestDto.builder()
                .ssh("ssh-rsa-test-key")
                .build();

        ServerResponseDto createdServer = serverService.addServer(newServer);

        Assertions.assertNotNull(createdServer);
        Assertions.assertNotNull(createdServer.getId());
        Assertions.assertEquals(newServer.getSsh(), createdServer.getSsh());

        ServerRequestDto updateDto = ServerRequestDto.builder()
                .ssh("ssh-rsa-updated-key")
                .build();

        ServerResponseDto updatedServer = serverService.updateServer(createdServer.getId(), updateDto);

        Assertions.assertNotNull(updatedServer);
        Assertions.assertEquals(createdServer.getId(), updatedServer.getId());
        Assertions.assertEquals("ssh-rsa-updated-key", updatedServer.getSsh());

        serverService.deleteServer(updatedServer.getId());

        ServerResponseDto deletedServer = serverService.getServer(updatedServer.getId());
        Assertions.assertNull(deletedServer);
    }

    @Test
    public void getServerById() {
        ServerRequestDto newServer = ServerRequestDto.builder()
                .ssh("ssh-test-find")
                .build();

        ServerResponseDto createdServer = serverService.addServer(newServer);
        Assertions.assertNotNull(createdServer);

        ServerResponseDto foundServer = serverService.getServer(createdServer.getId());

        Assertions.assertNotNull(foundServer);
        Assertions.assertEquals(createdServer.getId(), foundServer.getId());

        serverService.deleteServer(createdServer.getId());
    }
}
