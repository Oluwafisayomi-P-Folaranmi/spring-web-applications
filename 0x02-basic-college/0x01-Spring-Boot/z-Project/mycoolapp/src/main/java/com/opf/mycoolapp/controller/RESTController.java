package com.opf.mycoolapp.controller;

import com.opf.mycoolapp.coach.CricketCoach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RESTController {

    // The fields that will be injected
    public CricketCoach cricketCoach;

    // The constructor
    @Autowired
    public RESTController(CricketCoach cricketCoach) {
        this.cricketCoach = cricketCoach;
    }

    // Developer's name saved in `application.properties` file
    @Value("${developer.name}")
    public String developerName;

    @Value("${developer.team}")
    public String developerTeam;

    /*
     * The endpoint exposed to interact with the developer
     *
     * Expose "/" that return "Hello World"
     */
    @GetMapping("/")
    public StringBuilder sayHello() {
        StringBuilder greeting = new StringBuilder();
        greeting.append("Hello 'Engr. ");
        greeting.append(developerName + "' of team '");
        greeting.append(developerTeam + "'");
        return greeting;
    }

    /*
     * The endpoint exposed to interact with cricket coach
     *
     */
    @GetMapping("/daily-workout")
    public String getDailyWorkout() {
        return cricketCoach.getDailyWorkout();
    }
}
