package com.nianti.services;

import com.nianti.models.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Component
public class QuestionDao
{
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public QuestionDao(DataSource dataSource)
    {
        jdbcTemplate = new JdbcTemplate(dataSource);
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
        SqlRowSet row = jdbcTemplate.queryForRowSet(sql, quizId);

        while (row.next()) {
            int questionId = row.getInt("question_id");
            int questionNumber = row.getInt("question_number");
            String questionText = row.getString("question_text");

            Question question= new Question(questionId, quizId,questionNumber, questionText);

            questions.add(question);
        }
        return questions;
    }
}
