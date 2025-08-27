package org.codigo.booking.user.service;

import org.codigo.booking.user.model.User;
import org.codigo.booking.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> allUsers() {
        List<User> users = new ArrayList<>();
        userRepository.findAll()
                .forEach(user -> {
                    user.setPassword(null);
                    users.add(user);
                });
        return users;
    }

    public User findUserById(int id) {
        var user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found"));
        user.setPassword(null);
        return user;
    }
}
