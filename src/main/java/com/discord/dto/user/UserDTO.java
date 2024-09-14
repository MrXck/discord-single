package com.discord.dto.user;

import com.discord.pojo.User;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class UserDTO {

    @NotEmpty(message = "用户名不能为空")
    private String username;

    @NotEmpty(message = "密码不能为空")
    private String password;

    private String token;

    private User user;
}
