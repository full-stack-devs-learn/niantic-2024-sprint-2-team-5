package com.nianti.controllers;

import com.nianti.models.Question;
import com.nianti.services.QuestionDao;
import com.nianti.services.QuizDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
public class ResultController {

    @Autowired
    private QuizDao quizDao;

    @Autowired
    private QuestionDao questionDao;

    @Autowired
    private QuizController quizController;

    @GetMapping("/quizzes/{quizId}/result")
    public String getResultPage(@PathVariable int quizId, Model model) {

        var quiz = quizDao.getQuizById(quizId);

        if (quiz == null) {
            return "error/quiz-not-found";
        }

        model.addAttribute("quiz", quiz);
        model.addAttribute("pageTitle", "Quiz Results");

        return "quiz/result-page";
    }

    @PostMapping("/quizzes/{quizId}/submit")
    public String submitQuiz(@PathVariable int quizId, @RequestBody List<Integer> userAnswers, Model model) {

        // calculate the score using QuizController's method
        int score = quizController.calculateScore(userAnswers, quizId);

        // get the total question
        List<Question> questions = questionDao.getQuestionByQuizId(quizId);
        int totalQuestions = questions != null ? questions.size() : 0;


        model.addAttribute("score", score);
        model.addAttribute("totalQuestions", totalQuestions);
        model.addAttribute("quizId", quizId);

        return "redirect:/quizzes/" + quizId + "/result";
    }

}