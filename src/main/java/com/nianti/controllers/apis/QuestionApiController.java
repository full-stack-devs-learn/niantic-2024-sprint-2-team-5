package com.nianti.controllers.apis;

import com.nianti.models.Question;
import com.nianti.services.QuestionDao;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
@RestController
public class QuestionApiController {

        private QuestionDao questionsDao = new QuestionDao();


        @GetMapping("/api/questions/page/{page}")
        public ArrayList<Question> getAllActors(@PathVariable int page)
        {
            ArrayList<Question> questions;
            questions = questionsDao.getQuestions(page);
            return questions;
        }

        @GetMapping("/api/questions/{questionId}")
        public int getQuestionPageCount()
        {
            int totalCount = questionsDao.getQuestionsCount();
            int pages = totalCount / 10;
            if(totalCount % 10 > 0) pages ++;

            return pages;
        }
//api/questions/quizzes/quizid
    //no page total count return array list question ids

    }


