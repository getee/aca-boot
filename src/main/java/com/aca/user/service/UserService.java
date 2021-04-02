package com.aca.user.service;

import com.aca.user.entity.User;

public interface UserService {

    User create(User user);

    void update(User user);

    void delete(User user);

    User findByLoginName(String loginName);

    void deleteAll();

    /**
     * 通过ID 查找。
     * @param id id
     * @return 用户。
     */
    User findOne(long id);
}
