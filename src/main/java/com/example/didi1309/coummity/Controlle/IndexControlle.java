package com.example.didi1309.coummity.Controlle;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexControlle {
    @GetMapping("/")
    public String hello( Model model){
       // model.addAttribute("name",name);
        return "index";
    }
}
