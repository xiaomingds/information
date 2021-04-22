package com.example.information.entity;


import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;


import javax.validation.constraints.Size;

@Data
public class Login {

    @NotBlank(message = "登录名不能为空")
    String username;
    @Size(min = 6,message = "密碼不能低於6個字符")
    String password;

}
