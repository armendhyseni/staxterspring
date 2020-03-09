/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.staxter.user.exception;

/**
 *
 * @author Armend
 */
public class LoginFailedException extends RuntimeException {

    public LoginFailedException(String code, String description) {
        super(code + ";" + description);
    }
}
