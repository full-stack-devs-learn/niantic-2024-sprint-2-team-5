package com.nianti.controllers;

import com.nianti.models.Answer;
import com.nianti.models.Question;
import com.nianti.models.Quiz;
import com.nianti.services.AnswerDao;
import com.nianti.services.QuestionDao;
import com.nianti.services.QuizDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@Controller
public class QuizController {
    @Autowired
    private QuizDao quizDao;
    @Autowired
    private QuestionDao questionDao;

    @Autowired
    private AnswerDao answerDao;


    @GetMapping("/quizzes")

    public String getAllQuizzes(Model model) {
        List<Quiz> quizzes = quizDao.getAllQuizzes();
        model.addAttribute("quizzes", quizzes);
        model.addAttribute("pageTitle", "All Quizzes");

        return "quiz/index";
    }

    @GetMapping("quizzes/{quizId}")
    public String getQuizById(Model model, @PathVariable int quizId) {
        var quiz = quizDao.getQuizById(quizId);
        model.addAttribute("quiz", quiz);
        model.addAttribute("pageTitle", "Get Quiz by Id");

        return "quiz/quiz-page";
    }

    @GetMapping("/quizzes/add")
    public String addQuiz(Model model) {
        model.addAttribute("quiz", new Quiz());
        model.addAttribute("action", "add");
        model.addAttribute("pageTitle", "Add Quiz");

        return "quiz/add_edit";
    }

    @PostMapping("/quizzes/add")
    public String addQuiz(Model model, @ModelAttribute("quiz") Quiz quiz) {
        quizDao.addQuiz(quiz);
        model.addAttribute("quiz", quiz);

        return "redirect:/quizzes";
    }
    @GetMapping("quizzes/edit")
    public String editQuiz(Model model, @PathVariable int id)
    {
        Quiz quiz = quizDao.getQuizById(id);
        model.addAttribute("quiz", quiz);
       // model.addAttribute("question", question);
       // model.addAttribute("answer", answer)

        return "quiz/add_edit";
    }

    @PostMapping("quizzes/edit")
    public String editTransaction(@ModelAttribute("quiz") Quiz quiz, @PathVariable int id)
    {
        quiz.setQuizId(id);
        quizDao.updateQuiz(quiz);

        return "redirect: quiz/index";
    }
}



