package ru.dsobin.quiz.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import ru.dsobin.quiz.service.CsvQuestionService;
import ru.dsobin.quiz.reader.QuestionReader;

@Configuration
@PropertySource("classpath:application.properties")
public class QuizConfig {

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public Resource questionsResource(@Value("${quiz.questions.file}") String fileName) {
        return new ClassPathResource(fileName);
    }

    @Bean
    public QuestionReader questionReader(Resource questionsResource) {
        return new CsvQuestionService(questionsResource);
    }
}