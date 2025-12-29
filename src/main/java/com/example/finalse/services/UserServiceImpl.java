package com.example.finalse.services;

import com.example.finalse.entities.Permission;
import com.example.finalse.entities.User;
import com.example.finalse.repositories.PermissionRepository;
import com.example.finalse.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);

        if (Objects.nonNull(user)) {
            return user;
        }
        throw new UsernameNotFoundException("User Not Found");
    }

    private User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            User user = (User) authentication.getPrincipal();
            return user;
        }
        return null;
    }

    @Override
    public Boolean signUp(String email, String password, String repeatPassword) {
        User u = userRepository.findByEmail(email);

        if (Objects.isNull(u)) {
            if (password.equals(repeatPassword)) {
                List<Permission> permissions = new ArrayList<>();
                Permission simplePermission = permissionRepository.findByPermission("ROLE_USER");
                if (simplePermission != null) {
                    permissions.add(simplePermission);
                }

                User newUser = new User();
                newUser.setEmail(email);
                newUser.setPassword(passwordEncoder.encode(password));
                newUser.setPermissions(permissions);

                userRepository.save(newUser);
                return true;
            }
            return false;
        }
        return null;
    }

    @Override
    public Boolean updatePassword(String oldPassword, String newPassword, String repeatNewPassword) {
        User currentUser = getCurrentUser();

        if (Objects.nonNull(currentUser)) {
            if (passwordEncoder.matches(oldPassword, currentUser.getPassword())) {
                if (newPassword.equals(repeatNewPassword)) {
                    currentUser.setPassword(passwordEncoder.encode(newPassword));
                    userRepository.save(currentUser);
                    return true;
                }
                return false;
            }
            return null;
        }
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<Permission> getAllPermissions() {
        return permissionRepository.findAll();
    }
}
