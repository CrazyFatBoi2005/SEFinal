package com.example.finalse.services;

import com.example.finalse.entities.User;
import com.example.finalse.entities.Permission;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    Boolean signUp(String email, String password, String repeatPassword);

    Boolean updatePassword(String oldPassword, String newPassword, String repeatNewPassword);

    List<User> getAllUsers();

    User getUserById(Long id);

    void deleteUser(Long id);

    User updateUser(User user);

    List<Permission> getAllPermissions();
}
