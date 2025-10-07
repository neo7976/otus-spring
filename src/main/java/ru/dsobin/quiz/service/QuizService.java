package ru.dsobin.quiz.service;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.dsobin.quiz.model.Question;

import java.util.List;

@Service
public class QuizService {

    private final QuestionService questionService;
    private final int passingScore;

    public QuizService(QuestionService questionService,
                       @Value("${quiz.passing.score}") int passingScore) {
        this.questionService = questionService;
        this.passingScore = passingScore;
    }

    public void conductQuiz(ConsoleIOService io) {
        List<Question> questions = questionService.getQuestions();
        int correctAnswers = 0;

        io.print("Welcome to the Student Quiz!");
        io.print("Please answer the following questions:\n");

        for (int i = 0; i < questions.size(); i++) {
            Question q = questions.get(i);
            io.print("Question " + (i + 1) + ":");
            io.print(q.getText());

            if (!q.isFreeResponse()) {
                // Print options A, B, C...
                for (int j = 0; j < q.getOptions().size(); j++) {
                    char optionLetter = (char) ('A' + j);
                    io.print("  " + optionLetter + ". " + q.getOptions().get(j));
                }
                io.print("Your answer (A, B, C...):");
            } else {
                io.print("Your answer (free text):");
            }

            String userAnswer = io.readLine().toUpperCase();

            if (isAnswerCorrect(q, userAnswer)) {
                correctAnswers++;
                io.print("✅ Correct!\n");
            } else {
                io.print("❌ Incorrect.\n");
            }
        }

        boolean passed = correctAnswers >= passingScore;
        io.print("Quiz finished!");
        io.print("Correct answers: " + correctAnswers + " out of " + questions.size());
        io.print(passed ? "🎉 Congratulations! You passed the quiz." : "😔 Sorry, you did not pass.");
    }

    private boolean isAnswerCorrect(Question question, String userAnswer) {
        if (question.isFreeResponse()) {
            return !userAnswer.isEmpty() && !"(FREE)".equals(userAnswer);
        } else {
            if (userAnswer.length() != 1) {
                return false;
            }
            char c = userAnswer.charAt(0);
            if (c < 'A' || c > 'Z') {
                return false;
            }
            int selectedOptionIndex = c - 'A';

            if (selectedOptionIndex < 0 || selectedOptionIndex >= question.getOptions().size()) {
                return false;
            }

            return selectedOptionIndex == question.getCorrectAnswerIndex();
        }
    }
}
