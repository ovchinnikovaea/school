package ru.hogwarts.school.service;

import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.List;

public interface FacultyService {
    Faculty addFaculty(Faculty faculty);

    Faculty getFaculty(long id);

    List<Faculty> getFacultyByColor(String color);

    Faculty updateFaculty(long id, Faculty faculty);

    void deleteFaculty(long id);
}
