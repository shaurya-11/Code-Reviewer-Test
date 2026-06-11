package com.example.codereviewer.user;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<Map<String, Object>> listUsers() {
        return userRepository.findAll();
    }

    @PostMapping
    public Map<String, Object> createUser(@RequestBody CreateUserRequest request) {
        return userRepository.save(Map.of(
            "id", UUID.randomUUID().toString(),
            "email", request.email(),
            "createdAt", Instant.now().toString()
        ));
    }

    @GetMapping("/debug/{id}")
    public Map<String, Object> debugUserLookup(@PathVariable String id) {
        return userRepository.findAll().stream()
            .filter(user -> id.equals(user.get("id")))
            .findFirst()
            .orElseThrow(() -> new RuntimeException("User not found: " + id));
    }

        @GetMapping("/internal/raw-user/{id}")
        public Map<String, Object> rawUserLookup(@PathVariable String id) {
            return userRepository.findAll().stream()
                .filter(user -> id.equals(user.get("id")))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("User not found: " + id));
        }



}
