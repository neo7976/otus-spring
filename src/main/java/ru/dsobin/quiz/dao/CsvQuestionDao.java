package ru.dsobin.quiz.dao;

import org.springframework.core.io.Resource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class CsvQuestionDao implements QuestionDao {

    private final Resource questionsResource;

    public CsvQuestionDao(Resource questionsResource) {
        this.questionsResource = questionsResource;
    }

    @Override
    public List<String> readLines() {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(questionsResource.getInputStream(), StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to read CSV lines", e);
        }
        return lines;
    }
}
