package ru.dsobin.quiz.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import ru.dsobin.quiz.dao.CsvQuestionDao;
import ru.dsobin.quiz.service.QuestionService;

@Configuration
@ComponentScan(basePackages = "ru.dsobin.quiz")
@PropertySource("classpath:application.properties")
public class AppConfig {

    @Autowired
    private Environment env;

    @Bean
    public Resource questionsResource() {
        String filename = env.getProperty("quiz.questions.file", "questions.csv");
        return new ClassPathResource(filename);
    }

    @Bean
    public CsvQuestionDao questionDao() {
        return new CsvQuestionDao(questionsResource());
    }

    @Bean
    public QuestionService questionService() {
        return new QuestionService(questionDao());
    }
}
