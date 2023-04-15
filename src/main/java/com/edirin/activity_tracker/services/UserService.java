package com.edirin.activity_tracker.services;

import com.edirin.activity_tracker.dto.UserDTO;
import com.edirin.activity_tracker.model.Users;

public interface UserService {
    Users register(UserDTO userDTO);
    Users getUser(String username, String password);
}
