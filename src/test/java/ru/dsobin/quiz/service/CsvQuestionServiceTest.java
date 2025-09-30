package ru.dsobin.quiz.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import ru.dsobin.quiz.model.Question;
import ru.dsobin.quiz.reader.QuestionReader;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class CsvQuestionServiceTest {

    @Test
    void shouldReadQuestionsFromCsv() {
        QuestionReader reader = new CsvQuestionService(new ClassPathResource("questions.csv"));
        List<Question> questions = reader.readQuestions();

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