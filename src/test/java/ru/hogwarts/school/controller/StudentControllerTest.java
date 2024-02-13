package ru.hogwarts.school.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriComponentsBuilder;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StudentControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private StudentRepository studentRepository;


    @Autowired
    private TestRestTemplate restTemplate;

    @BeforeEach
    public void clear() {
        studentRepository.deleteAll();
    }

    @Test
    public void shouldCreateStudent() {
        //given
        Student student = new Student();
        student.setName("Ivan");
        student.setAge(20);

        //when
        Student actualStudent = restTemplate.postForObject("http://localhost:" + port + "/students", student, Student.class);

        //then
        assertNotNull(actualStudent.getId());
        assertEquals(actualStudent.getName(), student.getName());
        assertEquals(actualStudent.getAge(), student.getAge());
    }

    @Test
    public void shouldGetStudent() {
        //given
        Student student = studentRepository.save(new Student("Irina", 25));

        //when
        Student actualStudent = restTemplate.getForObject("http://localhost:" + port + "/students/" + student.getId(), Student.class);

        //then
        assertNotNull(actualStudent);
        assertEquals(actualStudent.getId(), student.getId());
        assertEquals(actualStudent.getName(), student.getName());
        assertEquals(actualStudent.getAge(), student.getAge());
    }

    @Test
    public void shouldUpdateStudent() {
        //given
        Student student = studentRepository.save(new Student("Irina", 25));

        Student newStudent = new Student("Irina", 30);

        //when
        HttpEntity<Student> requestEntity = new HttpEntity<>(newStudent);
        Student actualStudent = restTemplate.exchange(
                "http://localhost:" + port + "/students/" + student.getId(),
                HttpMethod.PUT,
                requestEntity,
                Student.class)
                .getBody();

        //then
        assertNotNull(actualStudent);
        assertEquals(actualStudent.getId(), student.getId());
        assertEquals(actualStudent.getName(), newStudent.getName());
        assertEquals(actualStudent.getAge(), newStudent.getAge());
    }

    @Test
    public void shouldDeleteStudent() {
        //given
        Student student = studentRepository.save(new Student("Irina", 25));

        //when

        ResponseEntity<Student> studentResponseEntity = restTemplate.exchange(
                        "http://localhost:" + port + "/students/" + student.getId(),
                        HttpMethod.DELETE,
                        null,
                        Student.class);
        //then
        assertNotNull(studentResponseEntity);
        assertEquals(studentResponseEntity.getStatusCode(), HttpStatusCode.valueOf(200));
        org.assertj.core.api.Assertions.assertThat(studentRepository.findById(student.getId())).isNotPresent();
    }

}
