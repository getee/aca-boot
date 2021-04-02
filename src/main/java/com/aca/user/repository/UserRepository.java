package com.aca.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aca.user.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * 通过账号查找。
     *
     * @param loginName 账号。
     * @return 用户。
     */
    User findByLoginName(String loginName);

    /**
     * 通过id查找。
     * @param id id。
     * @param deleted 是否删除。
     * @return 用户。
     */
    User findByIdAndDeleted(long l, boolean b);
}
