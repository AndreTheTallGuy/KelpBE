package com.Kelp2.kelp.DAO;

import com.Kelp2.kelp.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Integer> {
    User findUserByUserName(String userName);
    User findUserByEmail(String user);
}
