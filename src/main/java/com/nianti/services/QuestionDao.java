package com.nianti.services;

import com.nianti.models.Question;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.List;

@Component
public class QuestionDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public QuestionDao() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/trivio");
        dataSource.setUsername("root");
        dataSource.setPassword("P@ssw0rd");

        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Question getQuestionById(int questionId) {

        String sql = """
                SELECT question_id
                         , quiz_id
                         , question_number
                         , question_text
                FROM question
                WHERE question_id =?;
                """;
        var row = jdbcTemplate.queryForRowSet(sql, questionId);

        if (row.next()) {
            int quizId = row.getInt("quiz_id");
            int questionNumber = row.getInt("question_number");
            String questionText = row.getString("question_text");

            return  new Question(questionId, quizId, questionNumber, questionText);

        }
        return new Question();
    }
    public List<Question> getQuestionByQuizId(int quizId) {

        ArrayList<Question> questions = new ArrayList<>();
        String sql = """
                SELECT question_id
                         , quiz_id
                         , question_number
                         , question_text
                FROM question
                WHERE quiz_id =?;
                """;
        var row = jdbcTemplate.queryForRowSet(sql, quizId);

        while (row.next()) {
            int questionId = row.getInt("question_id");
            int questionNumber = row.getInt("question_number");
            String questionText = row.getString("question_text");

            Question question = new Question(questionId, quizId, questionNumber, questionText);

            questions.add(question);
        }
        return questions;
    }

    public int getQuestionsCount(int quizId) {
        try {
            String sql = """
                           SELECT count(*) AS question_count
                           FROM question
                           WHERE quiz_id =?;
                    """;
            var row = jdbcTemplate.queryForRowSet(sql, quizId);

            if (row.next()) {
                return row.getInt("question_count");
            }
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    public ArrayList<Question> getAllQuestions() {
        var questions = new ArrayList<Question>();

        String sql = """
                SELECT question_id
                    , quiz_id
                	, question_number
                    , question_text
                FROM question;
                """;

        var row = jdbcTemplate.queryForRowSet(sql);

        while (row.next()) {
            int questionId = row.getInt("question_id");
            int quizId = row.getInt("quiz_id");
            int questionNumber = row.getInt("question_number");
            String questionText = row.getString("question_text");

            var question = new Question(questionId, quizId, questionNumber, questionText);
            questions.add(question);
        }

        return questions;
    }

    public ArrayList<Question> getQuestions(int page) {
        int pageSize = 10;
        int skip = (page - 1) * pageSize;

        var questions = new ArrayList<Question>();

        String sql = """
                SELECT question_id
                    , quiz_id
                	, question_number
                    , question_text
                FROM question;
                LIMIT ?, ?;
                """;

        var row = jdbcTemplate.queryForRowSet(sql, skip, pageSize);

        while (row.next()) {
            int questionId = row.getInt("question_id");
            int quizId = row.getInt("quiz_id");
            int questionNumber = row.getInt("question_number");
            String questionText = row.getString("question_text");

            var question = new Question(questionId, quizId, questionNumber, questionText);
            questions.add(question);
        }
        return questions;
    }
}
