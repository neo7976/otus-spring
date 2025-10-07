package ru.dsobin.quiz.service;

import ru.dsobin.quiz.model.Question;

import java.util.List;

public class ConsoleOutputService {

    public void printQuestions(List<Question> questions) {
        System.out.println("Quiz Questions:\n");
        for (Question q : questions) {
            System.out.println(q);
        }
    }
}
