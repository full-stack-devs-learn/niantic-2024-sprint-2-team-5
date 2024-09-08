package com.nianti.controllers;

import com.nianti.models.Quiz;
import com.nianti.services.AnswerDao;
import com.nianti.services.QuestionDao;
import com.nianti.services.QuizDao;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/quizzes/{quizId}")
    public String getQuizById(Model model, @PathVariable int quizId) {

        var quiz = quizDao.getQuizById(quizId);

        int totalQuestions = questionDao.getQuestionsCount(quizId);

        model.addAttribute("quiz", quiz);
        model.addAttribute("totalQuestions", totalQuestions);
        model.addAttribute("pageTitle", "Get Quiz by Id");

        return "quiz/quiz-page";
    }

    @GetMapping("/quizzes/add")
    public String addQuiz(Model model)
    {
        model.addAttribute("quiz", new Quiz());
        model.addAttribute("action", "add");

        return "quiz/add-edit";
    }

    @PostMapping("/quizzes/add")
    public String addQuiz(Model model, @Valid @ModelAttribute("quiz") Quiz quiz, BindingResult result)
    {
        if(result.hasErrors())
        {
            model.addAttribute("isInvalid", true);
            model.addAttribute("action", "add");
            return "quiz/add-edit";
        }

        quizDao.addQuiz(quiz);
        return "redirect:/quizzes";
    }


    @GetMapping("/quizzes/{quizId}/edit")
    public String editQuiz(Model model, @PathVariable int quizId)
    {
        Quiz quiz = quizDao.getQuizById(quizId);
        model.addAttribute("quiz", quiz);
        model.addAttribute("action", "edit");
        return "quiz/add-edit";
    }

    @PostMapping("/quizzes/{quizId}/edit")
    public String editQuiz(Model model, @Valid @ModelAttribute("quiz") Quiz quiz, BindingResult result, @PathVariable int quizId)
    {
        if(result.hasErrors())
        {
            model.addAttribute("isInvalid", true);
            model.addAttribute("action", "edit");
            return "quiz/add-edit";
        }

        quiz.setQuizId(quizId);
        quizDao.updateQuiz(quiz);
        return "redirect:/quizzes";
    }
    @GetMapping("/quizzes/{quizId}/delete")
    public String deleteQuiz(Model model, @PathVariable int quizId)
    {
        var quiz = quizDao.getQuizById(quizId);

        if (quiz == null)
        {
            return "404";
        }

        model.addAttribute("quiz", quiz);
        return "quiz/delete";
    }

    @PostMapping("/quizzes/{quizId}/delete")
    public String deleteQuiz(@PathVariable int quizId)
    {
        quizDao.deleteQuiz(quizId);
        return "redirect:/quizzes";
    }
}



