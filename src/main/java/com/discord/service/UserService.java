package com.discord.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.discord.dto.user.RegisterUserDTO;
import com.discord.dto.user.UpdateUserDTO;
import com.discord.dto.user.UserDTO;
import com.discord.pojo.User;

public interface UserService extends IService<User> {

    UserDTO login(UserDTO userDTO);

    void updateUser(UpdateUserDTO updateUserDTO);

    void register(RegisterUserDTO registerUser);

}
