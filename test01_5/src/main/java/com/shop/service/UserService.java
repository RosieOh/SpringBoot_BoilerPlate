package com.shop.service;

import com.shop.entity.User;
import com.shop.entity.UserRole;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

public interface UserService {
    public void save(User user, Integer roleId);
    public List<User> findAll();
    public User findById(Long id);
    public User findByUserId(String username);
    public User findByEmail(String email);
    public User getLatestUser();
    public void update(User user);
    public User loginPro(User user);
    public UserRole getUserRole(Long userId);
    public PasswordEncoder passwordEncoder();
}
