package ru.dsobin.quiz.reader;

import ru.dsobin.quiz.model.Question;

import java.util.List;

public interface QuestionReader {
    List<Question> readQuestions();
}
