package com.chunjae.test03.per;

import com.chunjae.test03.entity.Euser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    List<Euser> getUserList();
    Euser getUser(String name);
    void getWithdraw(String name);
    void getActivate(String name);
    void getDormant(String name);
    Euser getByEmail(String email);
    Euser getByName(String name);
    Euser findById(String email, String tel);
    Euser findByPw(String email, String tel, String name);
    void userJoin(Euser euser);
    void updateUser(Euser euser);
    void updateLevel(String name, String lev);
}