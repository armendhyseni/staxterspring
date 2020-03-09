/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.staxter.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.staxter.user.controller.UserController;
import com.staxter.user.exception.UserAlreadyExistsException;
import com.staxter.user.service.UserRepositoryService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author Armend
 */
@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Mock
    UserRepositoryService repositoryService;

    @Test
    public void testAddUser() throws UserAlreadyExistsException, JsonProcessingException {
       
    }

}
