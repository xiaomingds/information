package com.example.information.mapper;


import com.example.information.entity.Permissions.MenuItem;
import com.example.information.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;


@Mapper
@Component
public interface  UserMapper {
    User get(int userid);
    User Login(String loginName);
    int CreatUser(User user);
    int DelUser(int userId);
    int UpdatelUser(User user);
    List<User> allUser();
    User FindUser(String userName);
    //shiro
    List<MenuItem> getMenu(String loginName);
}
