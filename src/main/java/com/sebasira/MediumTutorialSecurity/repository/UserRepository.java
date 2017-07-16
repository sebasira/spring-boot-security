package com.sebasira.MediumTutorialSecurity.repository;

import com.sebasira.MediumTutorialSecurity.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by sebas on 16/07/17.
 */
@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
