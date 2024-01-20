package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FacultyServiceImpl implements FacultyService{
    private final Map<Long, Faculty> faculties = new HashMap<>();

    private static long generatedFacultiesId = 0;

    @Override
    public Faculty addFaculty(Faculty faculty) {
        faculty.setId(++generatedFacultiesId);
        faculties.put(generatedFacultiesId, faculty);
        return faculty;
    }

    @Override
    public Faculty getFaculty(long id) {
        return faculties.get(id);
    }

    @Override
    public List<Faculty> getFacultyByColor(String color) {
        return faculties.values()
                .stream()
                .filter(it -> it.getColor().equals(color))
                .collect(Collectors.toList());
    }

    @Override
    public Faculty updateFaculty(long id, Faculty faculty) {
        Faculty updateFaculty = faculties.get(id);
        updateFaculty.setName(faculty.getName());
        updateFaculty.setColor(faculty.getColor());
        return updateFaculty;
    }

    @Override
    public Faculty deleteFaculty(long id) {
        return faculties.remove(id);
    }

}
