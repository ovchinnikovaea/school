package ru.hogwarts.school.controller;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repository.FacultyRepository;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FacultyControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private FacultyRepository facultyRepository;

    @Autowired
    private TestRestTemplate restTemplate;

    @BeforeEach
    public void clear() {
        facultyRepository.deleteAll();
    }

    @Test
    public void shouldCreateFaculty() {
        //given
        Faculty faculty = new Faculty();
        faculty.setName("Griffindor");
        faculty.setColor("red");

        //when
        Faculty actualFaculty = restTemplate.postForObject("http://localhost:" + port + "/faculties", faculty, Faculty.class);

        //then
        assertNotNull(actualFaculty.getId());
        assertEquals(actualFaculty.getName(), faculty.getName());
        assertEquals(actualFaculty.getColor(), faculty.getColor());
    }

    @Test
    public void shouldGetFaculty() {
        //given
        Faculty faculty = facultyRepository.save(new Faculty("Slytherin", "green"));

        //when
        Faculty actualFaculty = restTemplate.getForObject("http://localhost:" + port + "/faculties/" + faculty.getId(), Faculty.class);

        //then
        assertNotNull(actualFaculty);
        assertEquals(actualFaculty.getId(), faculty.getId());
        assertEquals(actualFaculty.getName(), faculty.getName());
        assertEquals(actualFaculty.getColor(), faculty.getColor());
    }

    @Test
    public void shouldUpdateFaculty() {
        //given
        Faculty faculty = facultyRepository.save(new Faculty("Slytherin", "green"));

        Faculty newFaculty = new Faculty("Slytherin", "red");
        //when
        HttpEntity<Faculty> requestEntity = new HttpEntity<>(newFaculty);
        Faculty actualFaculty = restTemplate.exchange(
                        "http://localhost:" + port + "/faculties/" + faculty.getId(),
                        HttpMethod.PUT,
                        requestEntity,
                        Faculty.class)
                .getBody();

        //then
        assertNotNull(actualFaculty);
        assertEquals(actualFaculty.getId(), faculty.getId());
        assertEquals(actualFaculty.getName(), newFaculty.getName());
        assertEquals(actualFaculty.getColor(), newFaculty.getColor());
    }

    @Test
    public void shouldDeleteFaculty() {
        //given
        Faculty faculty = facultyRepository.save(new Faculty("Slytherin", "green"));

        //when

        ResponseEntity<Faculty> facultyResponseEntity = restTemplate.exchange(
                "http://localhost:" + port + "/faculties/" + faculty.getId(),
                HttpMethod.DELETE,
                null,
                Faculty.class);
        //then
        assertNotNull(facultyResponseEntity);
        assertEquals(facultyResponseEntity.getStatusCode(), HttpStatusCode.valueOf(200));
        org.assertj.core.api.Assertions.assertThat(facultyRepository.findById(faculty.getId())).isNotPresent();
    }

}
