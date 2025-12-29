package com.example.finalse.controllers;

import com.example.finalse.dtos.LoginRequestDto;
import com.example.finalse.dtos.LoginResponseDto;
import com.example.finalse.dtos.UserResponseDto;
import com.example.finalse.entities.Permission;
import com.example.finalse.entities.User;
import com.example.finalse.mappers.UserMapper;
import com.example.finalse.services.UserService;
import com.example.finalse.services.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserServiceImpl userServiceImpl;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/registration")
    @PreAuthorize("isAnonymous()")
    public boolean registration(@RequestParam(name = "user_email") String email,
            @RequestParam(name = "user_password") String password,
            @RequestParam(name = "user_repeat_password") String repeatPassword) {

        return userService.signUp(email, password, repeatPassword);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto request) {
        try {
            User user = (User) userServiceImpl.loadUserByUsername(request.getEmail());

            if (passwordEncoder.matches(request.getPassword(), user.getPassword())) {
                LoginResponseDto response = LoginResponseDto.builder()
                        .id(user.getId())
                        .email(user.getEmail())
                        .roles(user.getPermissions().stream()
                                .map(p -> p.getPermission())
                                .collect(Collectors.toList()))
                        .message("Login successful")
                        .build();
                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(LoginResponseDto.builder()
                                .message("Invalid password")
                                .build());
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(LoginResponseDto.builder()
                            .message("Invalid email or password")
                            .build());
        }
    }

    @PostMapping("/save-password")
    @PreAuthorize("isAuthenticated()")
    public boolean savePassword(@RequestParam(name = "user_old_password") String oldPassword,
            @RequestParam(name = "user_new_password") String newPassword,
            @RequestParam(name = "user_repeat_new_password") String repeatNewPassword) {

        return userService.updatePassword(oldPassword, newPassword, repeatNewPassword);
    }

    @GetMapping("/")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public List<UserResponseDto> getAllUsers() {
        return userMapper.toDtoList(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public UserResponseDto getUser(@PathVariable(name = "id") Long id) {
        return userMapper.toDto(userService.getUserById(id));
    }

    @PostMapping("/save-user")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<UserResponseDto> saveUser(@RequestParam(name = "id") Long id,
            @RequestParam(name = "permissions", required = false) List<Long> permissionIds) {
        User user = userService.getUserById(id);
        if (user != null) {
            if (permissionIds != null) {
                List<Permission> permissions = new ArrayList<>();
                for (Long pId : permissionIds) {
                    Permission p = new Permission();
                    p.setId(pId);
                    permissions.add(p);
                }
                user.setPermissions(permissions);
            } else {
                user.setPermissions(new ArrayList<>());
            }
            User updatedUser = userService.updateUser(user);
            return ResponseEntity.ok(userMapper.toDto(updatedUser));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteUser(@PathVariable(name = "id") Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }
}
