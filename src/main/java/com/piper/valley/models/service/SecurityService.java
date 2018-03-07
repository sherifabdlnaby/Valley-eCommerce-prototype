package com.piper.valley.models.service;

public interface SecurityService {
    String findLoggedInUsername();

    void autologin(String username, String password);
}
