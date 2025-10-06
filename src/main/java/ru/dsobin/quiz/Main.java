package ru.dsobin.quiz;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.dsobin.quiz.config.AppConfig;
import ru.dsobin.quiz.service.ConsoleIOService;
import ru.dsobin.quiz.service.QuizService;

public class Main {
    public static void main(String[] args) {
        try (var context = new AnnotationConfigApplicationContext(AppConfig.class)) {
            QuizService quizService = context.getBean(QuizService.class);
            ConsoleIOService io = new ConsoleIOService();

            quizService.conductQuiz(io);
        }
    }
}