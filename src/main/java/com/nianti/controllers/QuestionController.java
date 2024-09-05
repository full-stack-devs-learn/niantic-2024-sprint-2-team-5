package com.nianti.controllers;

import com.nianti.services.QuestionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public class QuestionController {
    @Autowired
    private QuestionDao questionDao;

    @GetMapping("questions/{quizId}")
    public String getQuestionByQuizId(Model model, @PathVariable int quizId)
    {
        var question = questionDao.getQuestionByQuizId(quizId);
        model.addAttribute("question", question);
        model.addAttribute("pageTitle", "Get Question by quiz id");

        return "question-page";
    }
}
