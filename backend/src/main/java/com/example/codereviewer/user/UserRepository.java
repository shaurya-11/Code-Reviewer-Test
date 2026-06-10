package com.example.codereviewer.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {
    private final List<Map<String, Object>> users = new ArrayList<>();

    public List<Map<String, Object>> findAll() {
        return users;
    }

    public Map<String, Object> save(Map<String, Object> user) {
        users.add(user);
        return user;
    }
}

