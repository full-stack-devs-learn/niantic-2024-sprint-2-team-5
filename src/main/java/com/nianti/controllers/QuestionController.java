package com.nianti.controllers;

import com.nianti.models.Answer;
import com.nianti.models.Question;
import com.nianti.services.AnswerDao;
import com.nianti.services.QuestionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Controller
public class QuestionController {
    @Autowired
    private QuestionDao questionDao;
    @Autowired
    private AnswerDao answerDao;

//    @GetMapping("questions/{quizId}")
//    public String getQuestionByQuizId(Model model, @PathVariable int quizId)
//    {
//        var question = questionDao.getQuestionByQuizId(quizId);
//        model.addAttribute("question", question);
//        model.addAttribute("pageTitle", "Get Question by quiz id");
//
//        return "question-page";
//    }

    @GetMapping("/questions/{questionId}")
    public String getAllQuestions(Model model, @PathVariable int questionId)
    {
        var question = questionDao.getQuestionById(questionId);
        var answers = answerDao.getAnswersByQuestionId(questionId);
        question.setAnswers(answers);
        int questions = questionDao.getQuestionsCount(question.getQuizId());

        model.addAttribute("question", question);
        model.addAttribute("questionCount", questions);
        return "/questions/question-fragments";
    }
    @GetMapping("/questions/{quizId}/{index}")
    public String getQuestionByIndex(Model model, @PathVariable int quizId, @PathVariable int index) {
        List<Question> questions = questionDao.getQuestionByQuizId(quizId);


        model.addAttribute("questionCount", questions.size());

        if (index >= questions.size()) {
            return "quiz-end";
        }

        Question question = questions.get(index);
        List<Answer> answers = answerDao.getAnswersByQuestionId(question.getQuestionId());
        question.setAnswers(answers);

        model.addAttribute("question", question);
        return "/questions/question-fragments";
    }

}
