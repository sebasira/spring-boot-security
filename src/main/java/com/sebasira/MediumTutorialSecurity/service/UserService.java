package com.sebasira.MediumTutorialSecurity.service;

import com.sebasira.MediumTutorialSecurity.model.Client;
import com.sebasira.MediumTutorialSecurity.model.Role;
import com.sebasira.MediumTutorialSecurity.model.User;

import java.util.List;

/**
 * Created by sebas on 16/07/17.
 */
public interface UserService {
    public List<User> getAllUsers();
    public User findUserByEmail(String email);
    public void saveUser(User user);

    public List<Client> getClients(User user);
}
