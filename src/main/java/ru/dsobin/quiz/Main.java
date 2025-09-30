package ru.dsobin.quiz;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.dsobin.quiz.model.Question;
import ru.dsobin.quiz.reader.QuestionReader;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        QuestionReader reader = context.getBean(QuestionReader.class);

        System.out.println("Quiz Questions:\n");
        for (Question q : reader.readQuestions()) {
            System.out.println(q);
        }
    }
}