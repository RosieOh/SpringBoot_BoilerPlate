package com.chunjae.test06.per;

import com.chunjae.test06.entity.Euser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    List<Euser> getUserList();
    Euser getUser(String name);
    int getWithdraw(Integer id);
    int getActivate(String name);
    int getDormant(String name);
    Euser getByEmail(String email);
    Euser getByName(String name);
    Euser findById(String email, String tel);
    Euser findByPw(String email, String tel, String name);
    int userJoin(Euser euser);
    int updateUser(Euser euser);
    int updateLevel(String name, String lev);
    int removeUser(String name);
    Euser getUserById(Long id);
}