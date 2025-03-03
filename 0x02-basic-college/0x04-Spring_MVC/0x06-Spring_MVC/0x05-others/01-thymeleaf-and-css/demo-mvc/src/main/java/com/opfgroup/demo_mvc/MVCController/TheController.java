package com.opfgroup.demo_mvc.MVCController;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TheController {

    @GetMapping("/hello")
    public String sayHello(Model theModel) {

        theModel.addAttribute("theTime", java.time.LocalDateTime.now());
        return "hello";
    }
}
