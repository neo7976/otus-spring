package ru.dsobin.quiz.dao;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CsvQuestionDaoTest {
    private CsvQuestionDao dao;

    @BeforeEach
    void beforeEach() {
        dao = new CsvQuestionDao(new ClassPathResource("questions-test.csv"));
    }


    @AfterEach
    void tearDown() {
        dao = null;
    }

    @Test
    void shouldReadAllLinesFromCsv() {
        List<String> lines = dao.readLines();

        assertEquals(5, lines.size());
        assertEquals("What is Java?,0,Programming language,Scripting language,Markup language", lines.get(0));
        assertEquals("Free answer: What is your name?,(free)", lines.get(4));
    }
}