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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


import java.util.List;

@Controller
public class QuestionController {
    @Autowired
    private QuestionDao questionDao;
    @Autowired
    private AnswerDao answerDao;
    @Autowired
    private QuizDao quizDao;

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

        Question question = questions.get(index);
        List<Answer> answers = answerDao.getAnswersByQuestionId(question.getQuestionId());
        question.setAnswers(answers);

        model.addAttribute("question", question);
        return "/questions/question-fragments";
    }

    @GetMapping("/quizzes/{quizId}/questions/add")
    public String addQuestion(Model model, @PathVariable int quizId)
    {
        var quizzes = quizDao.getAllQuizzes();
        var quizTitle = quizDao.getQuizById(quizId).getTitle();

        model.addAttribute("question", new Question());
        model.addAttribute("quizzes", quizzes);
        model.addAttribute("selectedQuizId", quizId);
        model.addAttribute("selectedQuizTitle", quizTitle);
        model.addAttribute("action", "add");

        return "questions/add-edit";
    }

    @PostMapping("/quizzes/{quizId}/questions/add")
    public String addQuestion(Model model, @ModelAttribute("question") Question question, @PathVariable int quizId) {
        var quizzes = quizDao.getAllQuizzes();
        var quizTitle = quizDao.getQuizById(quizId).getTitle();

        model.addAttribute("quizzes", quizzes);
        model.addAttribute("selectedQuizId", quizId);
        model.addAttribute("selectedQuizTitle", quizTitle);
        model.addAttribute("isInvalid", false);
        model.addAttribute("action", "add");

        if (question != null) {
            questionDao.addQuestion(question);
            return "redirect:/quizzes";
        } else {
            model.addAttribute("isInvalid", true);
            return "questions/add-edit";
        }
    }

    @GetMapping("/questions/{questionId}/edit")
    public String editQuestion(Model model, @PathVariable int questionId)
    {
        var question = questionDao.getQuestionById(questionId);
        var quizzes = quizDao.getAllQuizzes();

        if(question == null)
        {
            return "404";
        }

        model.addAttribute("question", question);
        model.addAttribute("quizzes", quizzes);
        model.addAttribute("action", "edit");
        return "questions/add-edit";
    }

    @PostMapping("/questions/{questionId}/edit")
    public String editQuestion(Model model, @ModelAttribute("question") Question question, @PathVariable int questionId) {
        var quizzes = quizDao.getAllQuizzes();


        model.addAttribute("quizzes", quizzes);
        model.addAttribute("isInvalid", false);
        model.addAttribute("action", "edit");


        if (question == null) {
            model.addAttribute("isInvalid", true);
            return "questions/add-edit";
        }

        question.setQuestionId(questionId);
        questionDao.updateQuestion(question);

        return "redirect:/quizzes";
    }


    @GetMapping("/questions/{questionId}/delete")
    public String deleteQuestion(Model model, @PathVariable int questionId)
    {
        var question = questionDao.getQuestionById(questionId);

        if (question == null)
        {
            return "404";
        }

        model.addAttribute("question", question);
        return "questions/delete";
    }

    @PostMapping("/questions/{questionId}/delete")
    public String deleteQuestion(@PathVariable int questionId)
    {
        questionDao.deleteQuestion(questionId);
        return "redirect:/quizzes";
    }
}
