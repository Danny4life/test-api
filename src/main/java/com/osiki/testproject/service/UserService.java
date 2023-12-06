package com.osiki.testproject.service;

import com.osiki.testproject.model.Users;

import java.util.List;

public interface UserService {

    Users saveUser(Users users);

    List<Users> getAllUsers();

    Users getUserById(Long id);

    Users updateUsers(Long id, Users users);

    boolean deleteUser(Long id);
}
