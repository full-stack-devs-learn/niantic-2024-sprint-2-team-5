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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


@Controller
public class QuizController {
    @Autowired
    private QuizDao quizDao;
    @Autowired
    private QuestionDao questionDao;

    @Autowired
    private AnswerDao answerDao;

    @GetMapping("quizzes/{quizId}")
    public String getQuizById(Model model, @PathVariable int quizId)
    {
        var quiz = quizDao.getQuizById(quizId);
        model.addAttribute("quiz", quiz);
        model.addAttribute("pageTitle", "Get Quiz by Id");

        return "quiz/quiz-page";
    }
    @PostMapping("/quizzes/{quizId}/calculate-score")
    public int calculateScore(@RequestBody List<Integer> userAnswers, @PathVariable int quizId) {
        int correctAnswersCount = 0;
        List<Question> questions = questionDao.getQuestionByQuizId(quizId);

        if (questions == null || questions.isEmpty()) {
            return correctAnswersCount;
        }

        for (int i = 0; i < questions.size(); i++) {
            Question question = questions.get(i);
            int questionId = question.getQuestionId();
            List<Answer> answers = answerDao.getAnswersByQuestionId(questionId);

            if (answers != null && !answers.isEmpty()) {
                for (Answer answer : answers) {
                    if (answer.isCorrect() && userAnswers.get(i) == answer.getAnswerId()) {
                        correctAnswersCount++;
                        break;
                    }
                }
            }
        }
        return correctAnswersCount;
    }

    @GetMapping("/quizzes/{quizId}/correct-answers")
    public List<Question> getCorrectAnswers(@PathVariable int quizId) {
        List<Question> questions = questionDao.getQuestionByQuizId(quizId);

        if (questions == null || questions.isEmpty()) {
            return questions;
        }

        for (Question question : questions) {
            List<Answer> answers = answerDao.getAnswersByQuestionId(question.getQuestionId());

            if (answers != null && !answers.isEmpty()) {
                for (Answer answer : answers) {
                    if (answer.isCorrect()) {
                        question.getAnswers().add(answer);
                        break;
                    }
                }
            }
        }
        return questions;
    }
}



