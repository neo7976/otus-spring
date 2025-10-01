package ru.dsobin.quiz.service;

import ru.dsobin.quiz.model.Question;
import ru.dsobin.quiz.reader.QuestionReader;

import java.util.List;
import java.util.Scanner;

public class QuizService {

    private final QuestionReader questionReader;
    private final int passingScore;

    public QuizService(QuestionReader questionReader, int passingScore) {
        this.questionReader = questionReader;
        this.passingScore = passingScore;
    }

    public void runQuiz() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your first name: ");
        String firstName = scanner.nextLine().trim();
        System.out.print("Enter your last name: ");
        String lastName = scanner.nextLine().trim();

        System.out.println("\nHello, " + firstName + " " + lastName + "! Let's begin the quiz.\n");

        List<Question> questions = questionReader.readQuestions();

        // Ограничим до 5 вопросов
        int totalQuestions = Math.min(5, questions.size());
        int correctAnswers = 0;

        for (int i = 0; i < totalQuestions; i++) {
            Question q = questions.get(i);
            System.out.println("Question " + (i + 1) + ":");
            System.out.println(q.getText());

            if (q.isFreeResponse()) {
                System.out.print("Your answer: ");
                String answer = scanner.nextLine().trim();
                if (!answer.isEmpty()) {
                    correctAnswers++;
                }
            } else {
                List<String> options = q.getOptions();
                for (int j = 0; j < options.size(); j++) {
                    char optionLetter = (char) ('A' + j);
                    System.out.println("  " + optionLetter + ". " + options.get(j));
                }

                System.out.print("Your choice (A, B, C...): ");
                String input = scanner.nextLine().trim().toUpperCase();

                if (input.length() == 1) {
                    char choice = input.charAt(0);
                    int userIndex = choice - 'A';
                    if (userIndex >= 0 && userIndex < options.size()) {
                        // Сравниваем с правильным индексом из вопроса
                        if (userIndex == q.getCorrectAnswerIndex()) {
                            correctAnswers++;
                        }
                    }
                }
            }
            System.out.println(); // пустая строка между вопросами
        }

        // Вывод результата
        System.out.println("Quiz completed!");
        System.out.println("Correct answers: " + correctAnswers + " out of " + totalQuestions);

        if (correctAnswers >= passingScore) {
            System.out.println("Congratulations! You passed the quiz.");
        } else {
            System.out.println("Sorry, you did not pass. Try again next time.");
        }
    }
}