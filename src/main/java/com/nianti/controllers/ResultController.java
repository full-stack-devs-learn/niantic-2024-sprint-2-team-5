package com.nianti.controllers;

import com.nianti.models.Answer;
import com.nianti.models.Question;
import com.nianti.services.AnswerDao;
import com.nianti.services.QuestionDao;
import com.nianti.services.QuizDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


import java.util.List;

@Controller
public class ResultController {

    @Autowired
    private QuestionDao questionDao;

    @Autowired
    private AnswerDao answerDao;

    @GetMapping("/quizzes/{quizId}/correct-answers")
    public String getCorrectAnswers(@PathVariable int quizId, Model model) {
        List<Question> questions = questionDao.getQuestionByQuizId(quizId);

        for (Question question : questions) {
            List<Answer> answers = answerDao.getAnswersByQuestionId(question.getQuestionId());
            for (Answer answer : answers) {
                if (answer.isCorrect()) {
                    question.getAnswers().add(answer);
                }
            }
        }

        model.addAttribute("questions", questions);
        model.addAttribute("result", "results");
        return "/questions/question-fragments";
    }
}