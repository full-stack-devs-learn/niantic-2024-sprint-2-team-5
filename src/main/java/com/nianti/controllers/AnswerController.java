package com.nianti.controllers;

import com.nianti.services.AnswerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class AnswerController
{
    @Autowired
    private AnswerDao answerDao;

    @GetMapping("answers/{questionId}")
    public String getAnswersByQuestionId(Model model, @PathVariable int questionId)
    {
        var answer = answerDao.getAnswersByQuestionId(questionId);
        model.addAttribute("answer", answer);
        model.addAttribute("pageTitle", "Get Answer by question Id");

        return "answer-page";
    }
}
