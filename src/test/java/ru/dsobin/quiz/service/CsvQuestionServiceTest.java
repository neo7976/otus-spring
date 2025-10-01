package ru.dsobin.quiz.service;

import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import ru.dsobin.quiz.model.Question;
import ru.dsobin.quiz.reader.QuestionReader;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CsvQuestionServiceTest {

    @Test
    void shouldReadQuestionsWithCorrectAnswerIndex() {
        QuestionReader reader = new CsvQuestionService(new ClassPathResource("test-questions.csv"));
        List<Question> questions = reader.readQuestions();

        assertEquals(3, questions.size());

        Question q1 = questions.get(0);
        assertFalse(q1.isFreeResponse());
        assertEquals(0, q1.getCorrectAnswerIndex());
        assertEquals("Programming language", q1.getOptions().get(0));

        Question q2 = questions.get(1);
        assertFalse(q2.isFreeResponse());
        assertEquals(2, q2.getCorrectAnswerIndex());
        assertEquals("goto", q2.getOptions().get(2));

        Question q3 = questions.get(2);
        assertTrue(q3.isFreeResponse());
        assertNull(q3.getCorrectAnswerIndex());
    }
}