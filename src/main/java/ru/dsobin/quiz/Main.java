package ru.dsobin.quiz;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.dsobin.quiz.service.ConsoleOutputService;
import ru.dsobin.quiz.service.QuestionService;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        QuestionService questionService = context.getBean(QuestionService.class);
        ConsoleOutputService consoleOutputService = context.getBean(ConsoleOutputService.class);

        var questions = questionService.getQuestions();
        consoleOutputService.printQuestions(questions);
    }
}