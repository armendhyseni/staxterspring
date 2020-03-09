/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.staxter.user.repository;

import com.staxter.user.exception.LoginFailedException;
import com.staxter.user.exception.UserAlreadyExistsException;
import com.staxter.user.model.User;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Armend
 */
@Repository
public interface UserRepository {

    User createUser(User user) throws UserAlreadyExistsException;

    User loginUser(User user) throws LoginFailedException;

    List<User> getUsers();
}
