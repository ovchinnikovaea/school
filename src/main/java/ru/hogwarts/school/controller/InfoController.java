package ru.hogwarts.school.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.amqp.RabbitProperties;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hogwarts.school.model.Student;

import javax.sound.sampled.Port;
import java.util.stream.Stream;

@RestController
@RequestMapping("/port")
public class InfoController {

    @Value("${server.port}")
    private Integer serverport;

    @GetMapping
    public Integer get() {
        return serverport;
    }

    @GetMapping("sum")
    public int calculate() {

        //метод 1
        long startTime = System.currentTimeMillis();
        int sum = Stream
                .iterate(1, a -> a +1)
                .limit(1_000_000)
                .reduce(0, (a, b) -> a + b);
        long endTime = System.currentTimeMillis();
        long timeElapsed = endTime - startTime;
        System.out.println("Метод 1 занял " + timeElapsed + " мс");

        //метод 2
        startTime = System.currentTimeMillis();
        sum = Stream
                .iterate(1, a -> a +1)
                .limit(1_000_000)
                .parallel()
                .reduce(0, (a, b) -> a + b);
        endTime = System.currentTimeMillis();
        timeElapsed = endTime - startTime;
        System.out.println("Метод 2 занял " + timeElapsed + " мс");

        //метод 3
        startTime = System.currentTimeMillis();
        sum = 0;
        for (int i = 0; i < 1000000; i++) {
            sum += 1;
        }
        endTime = System.currentTimeMillis();
        timeElapsed = endTime - startTime;
        System.out.println("Метод 3 занял " + timeElapsed + " мс");
        return sum;
    }
}
