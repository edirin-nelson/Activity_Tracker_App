package com.edirin.activity_tracker.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import org.mindrot.jbcrypt.BCrypt;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String username;
    private String password;
    private String salt;

    public Users(String username, String password) {
        this.username = username;
        this.salt = BCrypt.gensalt();
        this.password = BCrypt.hashpw(password, salt);
    }

    public boolean checkPassword(String password, String salt) {
        return BCrypt.checkpw(password, salt);
    }
}
