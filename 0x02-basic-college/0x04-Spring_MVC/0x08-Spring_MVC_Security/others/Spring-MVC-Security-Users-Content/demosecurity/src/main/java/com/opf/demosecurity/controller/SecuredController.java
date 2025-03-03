package com.opf.demosecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecuredController {

    @GetMapping("/")
    public String showHome() {

        return "home";
    }

    // add a request mapping for /leaders
    @GetMapping("/leaders")
    public String showLeadersPage() {

        return "leaders";
    }

    // add a request mapping for /systems
    @GetMapping("/systems")
    public String showSystemsPage() {

        return "systems";
    }
}
