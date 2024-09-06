package com.nianti.controllers;

import com.nianti.models.Question;
import com.nianti.services.QuestionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;

@Controller
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

    @GetMapping("/questions/{questionId}")
    public String getAllQuestions(Model model, @PathVariable int id)
    {
        int questions;
        questions = questionDao.getQuestionsCount();

        StringBuilder builder = new StringBuilder();

        model.addAttribute("questions", questions);
        return "/questions/question-fragments";
    }

}
