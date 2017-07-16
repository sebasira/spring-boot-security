package com.sebasira.MediumTutorialSecurity.service;

import com.sebasira.MediumTutorialSecurity.model.User;

/**
 * Created by sebas on 16/07/17.
 */
public interface UserService {
    public User findUserByEmail(String email);
    public void saveUser(User user);
}
