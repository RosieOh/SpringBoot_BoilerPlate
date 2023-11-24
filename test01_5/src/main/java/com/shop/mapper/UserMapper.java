package com.shop.mapper;

import com.shop.entity.Role;
import com.shop.entity.User;
import com.shop.entity.UserRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    void save(User user);
    List<User> findAll();
    User findById(Long id);
    User findByUserId(String username);
    User findByEmail(String email);
    User getLatestUser();
    void update(User user);
    void setUserRole(UserRole userRole);
    UserRole getUserRole(Long userId);
    Role getRole(Integer id);
}
