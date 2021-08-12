package com.coven.jwt.repository;

import com.coven.jwt.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {
    public User findByUsername(String username);
//    public User findByUsernameAndNumber(String username, String number);
    public User findByUsernameAndPassword(String username, String password);

}
