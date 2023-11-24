package com.chunjae.test03.biz;

import com.chunjae.test03.entity.Euser;
import com.chunjae.test03.per.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<Euser> getUserList() {
        return userMapper.getUserList();
    }

    @Override
    public Euser getUser(String name) {
        return userMapper.getUser(name);
    }

    @Override
    public void getWithdraw(String name) {
        userMapper.getWithdraw(name);
    }

    @Override
    public void getActivate(String name) {
        userMapper.getActivate(name);
    }

    @Override
    public void getDormant(String name) {
        userMapper.getDormant(name);
    }

    @Override
    public Euser getByEmail(String email) {
        return userMapper.getByEmail(email);
    }

    @Override
    public Euser getByName(String name) {
        return userMapper.getByName(name);
    }

    @Override
    public Euser findById(String email, String tel) {
        return userMapper.findById(email, tel);
    }

    @Override
    public Euser findByPw(String email, String tel, String name) {
        return userMapper.findByPw(email, tel, name);
    }

    @Override
    public void userJoin(Euser euser) {
        userMapper.userJoin(euser);
    }

    @Override
    public void updateUser(Euser euser) {
        userMapper.updateUser(euser);
    }

    @Override
    public void updateLevel(String name, String lev) {
        userMapper.updateLevel(name, lev);
    }
}
