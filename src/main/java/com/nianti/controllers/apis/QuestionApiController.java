package com.nianti.controllers.apis;

import com.nianti.models.Question;
import com.nianti.services.QuestionDao;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class QuestionApiController {

        private QuestionDao questionsDao = new QuestionDao();


       @GetMapping("/api/questions/page/{page}")
        public List<Question> getQuestion(@PathVariable int quizId)
        {
            List<Question> questions;
            questions = questionsDao.getQuestionByQuizId(quizId);
            return questions;
        }
    }


