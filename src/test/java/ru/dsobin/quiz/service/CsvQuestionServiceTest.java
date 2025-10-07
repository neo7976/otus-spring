package ru.dsobin.quiz.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import ru.dsobin.quiz.dao.CsvQuestionDao;
import ru.dsobin.quiz.model.Question;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CsvQuestionServiceTest {

    private QuestionService service;
    private CsvQuestionDao dao;

    @BeforeEach
    void beforeEach() {
        dao = new CsvQuestionDao(new ClassPathResource("questions-test.csv"));
        service = new QuestionService(dao);
    }


    @AfterEach
    void tearDown() {
        dao = null;
        service = null;
    }

    @Test
    void shouldReadAndParseQuestionsFromCsv() {

        // Act
        List<Question> questions = service.getQuestions();

        // Assert
        assertNotNull(questions);
        assertEquals(5, questions.size());

        Question first = questions.get(0);
        assertEquals("What is Java?", first.getText());
        assertFalse(first.isFreeResponse());
        assertEquals(3, first.getOptions().size());

        Question last = questions.get(4);
        assertTrue(last.isFreeResponse());
    }
}