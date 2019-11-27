package com.example.didi1309.coummity.Controlle;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PubliscControlle {
    @GetMapping("/publish")
    public String publish(){
        return "publish";
    }
}
