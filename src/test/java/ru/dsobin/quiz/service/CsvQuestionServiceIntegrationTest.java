package ru.dsobin.quiz.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import ru.dsobin.quiz.config.QuizConfig;
import ru.dsobin.quiz.model.Question;
import ru.dsobin.quiz.reader.QuestionReader;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringJUnitConfig(QuizConfig.class)
class CsvQuestionServiceIntegrationTest {

    @Autowired
    private QuestionReader questionReader;

    @Test
    void shouldReadQuestionsFromCsvViaSpringContext() {
        List<Question> questions = questionReader.readQuestions();

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