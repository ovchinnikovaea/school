package ru.hogwarts.school.service;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService{
    private final Map<Long, Student> students = new HashMap<>();
    private static long generatedStudentsId = 0;

    @Override
    public Student addStudent(Student student) {
        student.setId(++generatedStudentsId);
        students.put(generatedStudentsId, student);
        return student;
    }
    @Override
    public Student getStudent(long id) {
        return students.get(id);
    }

    @Override
    public List<Student> getStudentByAge(int age) {
        return students.values()
                .stream()
                .filter(it -> it.getAge() == age)
                .collect(Collectors.toList());
    }

    @Override
    public Student updateStudent(long id, Student student) {
        Student updateStudent = students.get(id);
        updateStudent.setName(student.getName());
        updateStudent.setAge(student.getAge());
        return updateStudent;
    }
    @Override
    public Student deleteStudent(long id) {
       return students.remove(id);
    }
}
