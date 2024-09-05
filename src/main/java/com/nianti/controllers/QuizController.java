package com.nianti.controllers;

import ch.qos.logback.core.model.Model;
import com.nianti.models.Quiz;
import com.nianti.services.QuizDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.ArrayList;


@Controller
public class QuizController {
    @Autowired
    private QuizDao quizDao;

    @GetMapping("/quiz/index")
    public String getAllQuiz(Model model, @RequestParam(required = false) String quiz) {
        ArrayList<Quiz> quizzes;

        quizzes = (ArrayList<Quiz>) quizDao.getAllQuizzes();

      

        return "/quiz/index";
    }
}

