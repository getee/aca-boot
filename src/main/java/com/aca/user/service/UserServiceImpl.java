package com.aca.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.aca.user.entity.User;
import com.aca.user.repository.UserRepository;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repos;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User create(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return repos.save(user);
    }

    @Override
    public void update(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        repos.save(user);
    }

    @Override
    public void delete(User user) {
        repos.deleteById(user.getId());
    }

    @Override
    public User findByLoginName(String loginName) {
        return repos.findByLoginName(loginName);
    }

    @Override
    public void deleteAll() {
        repos.deleteAll();
    }

    @Override
    public User findOne(long id) {
        return repos.findByIdAndDeleted(1L, false);
    }

}
