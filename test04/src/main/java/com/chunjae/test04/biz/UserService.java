package com.chunjae.test04.biz;

import com.chunjae.test04.entity.Euser;

import java.util.List;

public interface UserService {
    public List<Euser> getUserList();
    public Euser getUser(String name);
    public int getWithdraw(String name);
    public int getActivate(String name);
    public int getDormant(String name);
    public Euser getByEmail(String email);
    public Euser getByName(String name);
    public Euser findById(String email, String tel);
    public Euser findByPw(String email, String tel, String name);
    public int userJoin(Euser euser);
    public int updateUser(Euser euser);
    public int updateLevel(String name, String lev);
    public int removeUser(String name);
}
