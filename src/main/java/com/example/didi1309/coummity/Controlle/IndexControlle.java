package com.example.didi1309.coummity.Controlle;

import com.example.didi1309.coummity.mapper.UserMapper;
import com.example.didi1309.coummity.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class IndexControlle {
    @Autowired
    private UserMapper userMapper;
    //当访问首页时，通过cookie在数据库中寻找token，如果token存在，用户存在，写入session
    @GetMapping("/")
    public String hello(HttpServletRequest request){
       // model.addAttribute("name",name);
        Cookie[] cookies=request.getCookies();
        if (cookies!=null){
        for (Cookie cookie:cookies) {
            if (cookie.getName().equals("token")){
                String token=cookie.getValue();
                User user = userMapper.findByToken(token);
                if (user!=null){
                    request.getSession().setAttribute("User",user);
                }
                break;
            }
        }}
        return "index";
    }
}
