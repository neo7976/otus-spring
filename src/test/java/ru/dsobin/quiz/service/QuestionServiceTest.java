package ru.dsobin.quiz.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import ru.dsobin.quiz.dao.CsvQuestionDao;
import ru.dsobin.quiz.model.Question;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class QuestionServiceTest {

    @Test
    @DisplayName("Should parse valid CSV into Questions with correct indices")
    void shouldParseValidCsv() {
        var dao = new CsvQuestionDao(new ClassPathResource("questions-test.csv"));
        var service = new QuestionService(dao);

        List<Question> questions = service.getQuestions();

        assertEquals(5, questions.size());

        // Q1: multiple choice
        Question q1 = questions.get(0);
        assertEquals("What is Java?", q1.getText());
        assertFalse(q1.isFreeResponse());
        assertEquals(0, q1.getCorrectAnswerIndex());
        assertEquals(List.of("Programming language", "Scripting language", "Markup language"), q1.getOptions());

        // Q2: multiple choice
        Question q2 = questions.get(1);
        assertEquals("What does JVM stand for?", q2.getText());
        assertEquals(1, q2.getCorrectAnswerIndex());
        assertEquals(List.of("Java Variable Machine","Java Virtual Machine","Just Very Magic"), q2.getOptions());

        // Q3: free response
        Question q3 = questions.get(4);
        assertEquals("Free answer: What is your name?", q3.getText());
        assertTrue(q3.isFreeResponse());
        assertNull(q3.getCorrectAnswerIndex());
        assertEquals(List.of("(free)"), q3.getOptions());
    }

    @Test
    @DisplayName("Should throw exception on invalid correct answer index")
    void shouldFailOnInvalidIndex() {
        var dao = new CsvQuestionDao(new ClassPathResource("questions-invalid.csv"));
        var service = new QuestionService(dao);

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                service::getQuestions
        );

        assertTrue(exception.getMessage().contains("Invalid correct answer index"));
    }
}