package ru.dsobin.quiz.service;

import ru.dsobin.quiz.dao.QuestionDao;
import ru.dsobin.quiz.model.Question;

import java.util.ArrayList;
import java.util.List;

public class QuestionService {

    private final QuestionDao questionDao;

    public QuestionService(QuestionDao questionDao) {
        this.questionDao = questionDao;
    }

    public List<Question> getQuestions() {
        List<String> lines = questionDao.readLines();
        List<Question> questions = new ArrayList<>();

        for (String line : lines) {
            String[] parts = line.split(",", -1);
            if (parts.length < 1) continue;

            String questionText = parts[0];
            List<String> options = new ArrayList<>();
            for (int i = 1; i < parts.length; i++) {
                options.add(parts[i].trim());
            }
            questions.add(new Question(questionText, options));
        }

        return questions;
    }
}