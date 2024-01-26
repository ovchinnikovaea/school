package ru.hogwarts.school.service;

import ru.hogwarts.school.model.Student;

import java.util.List;

public interface StudentService {
    Student addStudent(Student student);

    Student getStudent(long id);

    List<Student> getStudentByAge(int age);

    Student updateStudent(long id, Student student);

    void deleteStudent(Long id);
}
