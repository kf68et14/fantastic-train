package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
@AllArgsConstructor
public enum Role {
    USER("USER"),
    MANAGER("MANAGER"),
    ADMIN("ADMIN");

    private String value;

    private static final Map<String, Role> roleMap = new HashMap<>();

    static {
        for (Role role : Role.values()) {
            roleMap.put(role.value, role);
        }
    }

    public static Role of(String s) {
        return roleMap.get(s);
    }

}
