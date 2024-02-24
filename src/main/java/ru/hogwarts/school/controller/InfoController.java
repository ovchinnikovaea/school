package ru.hogwarts.school.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hogwarts.school.model.Student;

import javax.sound.sampled.Port;

@RestController
@RequestMapping("/port")
public class InfoController {

    @Value("${server.port}")
    private Integer serverport;

    @GetMapping
    public Integer get() {
        return serverport;
    }

}
