package ru.hogwarts.school.service;

public class StudentsNotFoundException extends RuntimeException {
    public StudentsNotFoundException(String massage) {
        super(massage);
    }
}
