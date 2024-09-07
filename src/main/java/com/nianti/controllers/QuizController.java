package com.nianti.controllers;

import com.nianti.services.QuizDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@Controller
public class QuizController {
    @Autowired
    private QuizDao quizDao;

    @GetMapping("quizzes/{quizId}")
    public String getQuizById(Model model, @PathVariable int quizId)
    {
        var quiz = quizDao.getQuizById(quizId);
        model.addAttribute("quiz", quiz);
        model.addAttribute("pageTitle", "Get Quiz by Id");

        return "quiz/quiz-page";
    }

}

