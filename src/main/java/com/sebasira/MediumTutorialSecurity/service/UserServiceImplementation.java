package com.sebasira.MediumTutorialSecurity.service;


import com.sebasira.MediumTutorialSecurity.model.Client;
import com.sebasira.MediumTutorialSecurity.model.Role;
import com.sebasira.MediumTutorialSecurity.model.User;
import com.sebasira.MediumTutorialSecurity.repository.RoleRepository;
import com.sebasira.MediumTutorialSecurity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Service("userService")
public class UserServiceImplementation implements UserService{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(1);

        if (user.isAsAdmin()) {
            Role userRole = roleRepository.findByRole("ADMIN");
            user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        }else{
            Role userRole = roleRepository.findByRole("USER");
            user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        }
        
        userRepository.save(user);
    }


    @Override
    public List<Client> getClients(User user) {
        return (new ArrayList<Client>(user.getClients()));
    }
}
