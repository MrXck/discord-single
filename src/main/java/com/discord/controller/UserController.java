package com.discord.controller;

import com.discord.dto.user.RegisterUserDTO;
import com.discord.dto.user.UpdateUserDTO;
import com.discord.dto.user.UserDTO;
import com.discord.service.UserService;
import com.discord.utils.Log;
import com.discord.utils.NoAuthorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    @NoAuthorization
    public UserDTO login(@RequestBody @Valid UserDTO userDTO) {
        return userService.login(userDTO);
    }

    @Log
    @PostMapping("/update")
    public void update(@RequestBody @Valid UpdateUserDTO updateUserDTO) {
        userService.updateUser(updateUserDTO);
    }

    @PostMapping("/register")
    @NoAuthorization
    public void register(@RequestBody @Valid RegisterUserDTO registerUser) {
        userService.register(registerUser);
    }

}
