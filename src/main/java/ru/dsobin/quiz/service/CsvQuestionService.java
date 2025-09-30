package ru.dsobin.quiz.service;

import org.springframework.core.io.Resource;
import ru.dsobin.quiz.model.Question;
import ru.dsobin.quiz.reader.QuestionReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class CsvQuestionService implements QuestionReader {

    private final Resource questionsResource;

    public CsvQuestionService(Resource questionsResource) {
        this.questionsResource = questionsResource;
    }

    @Override
    public List<Question> readQuestions() {
        List<Question> questions = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(questionsResource.getInputStream(), StandardCharsets.UTF_8))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",", -1);
                if (parts.length < 1) continue;

                String questionText = parts[0];
                List<String> options = new ArrayList<>();
                for (int i = 1; i < parts.length; i++) {
                    options.add(parts[i].trim());
                }
                questions.add(new Question(questionText, options));
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to read questions from CSV", e);
        }
        return questions;
    }
}