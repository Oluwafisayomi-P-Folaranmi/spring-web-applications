package com.opf.office.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/security")
public class SecurityController {

    @GetMapping("/hello/{developer-name}") // `/security/hello/<developer-name>`
    public String sayHello(@PathVariable("developer-name") String developerName) {

        return "Hello " + developerName;
    }
}
