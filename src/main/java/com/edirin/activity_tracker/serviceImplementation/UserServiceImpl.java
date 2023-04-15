package com.edirin.activity_tracker.serviceImplementation;

import com.edirin.activity_tracker.dto.UserDTO;
import com.edirin.activity_tracker.model.Users;
import com.edirin.activity_tracker.repository.UserRepository;
import com.edirin.activity_tracker.services.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Users register(UserDTO userDTO) {
        if (userDTO == null)
            return null;

        Users user = new Users();
        user.setName(userDTO.getName());
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());

        return userRepository.save(user);
    }

    @Override
    public Users getUser(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password).orElse(null);
    }
}
