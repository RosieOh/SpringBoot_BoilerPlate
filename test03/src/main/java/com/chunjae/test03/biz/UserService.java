package com.chunjae.test03.biz;

import com.chunjae.test03.entity.Euser;

import java.util.List;

public interface UserService {
    public List<Euser> getUserList();
    public Euser getUser(String name);
    public void getWithdraw(String name);
    public void getActivate(String name);
    public void getDormant(String name);
    public Euser getByEmail(String email);
    public Euser getByName(String name);
    public Euser findById(String email, String tel);
    public Euser findByPw(String email, String tel, String name);
    public void userJoin(Euser euser);
    public void updateUser(Euser euser);
    public void updateLevel(String name, String lev);
}
