package ru.hogwarts.school.service;

public class FacultiesNotFoundException extends RuntimeException {

    public FacultiesNotFoundException(String massage) {
        super(massage);
    }
}
