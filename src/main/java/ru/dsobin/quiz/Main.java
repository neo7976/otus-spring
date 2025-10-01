package ru.dsobin.quiz;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.dsobin.quiz.config.QuizConfig;
import ru.dsobin.quiz.service.QuizService;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(QuizConfig.class);

        // Получаем passing score из properties
        int passingScore = Integer.parseInt(
                context.getEnvironment().getProperty("quiz.passing.score", "4")
        );

        QuizService quizService = new QuizService(
                context.getBean("questionReader", ru.dsobin.quiz.reader.QuestionReader.class),
                passingScore
        );

        quizService.runQuiz();
    }
}