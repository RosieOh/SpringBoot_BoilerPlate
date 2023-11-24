package com.chunjae.test04.biz;

import com.chunjae.test04.entity.Euser;
import com.chunjae.test04.per.UserMapper;
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
    public int getWithdraw(String name) {
        return userMapper.getWithdraw(name);
    }

    @Override
    public int getActivate(String name) {
        return userMapper.getActivate(name);
    }

    @Override
    public int getDormant(String name) {
        return userMapper.getDormant(name);
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
    public int userJoin(Euser euser) {
        return userMapper.userJoin(euser);
    }

    @Override
    public int updateUser(Euser euser) {
        return userMapper.updateUser(euser);
    }

    @Override
    public int updateLevel(String name, String lev) {
        return userMapper.updateLevel(name, lev);
    }

    @Override
    public int removeUser(String name) { return userMapper.removeUser(name);    }
}
