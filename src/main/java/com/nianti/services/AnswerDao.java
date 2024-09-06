package com.nianti.services;

import com.nianti.models.Answer;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Component
public class AnswerDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public AnswerDao() {

        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/trivio");
        dataSource.setUsername("root");
        dataSource.setPassword("P@ssw0rd");

        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<Answer> getAnswersByQuestionId(int questionId) {
        ArrayList<Answer> answers = new ArrayList<>();

        String sql = """
                SELECT answer_id
                ,question_id
                ,answer_text
                ,is_correct
                FROM answer
                WHERE question_id = ?;
                """;
        SqlRowSet row = jdbcTemplate.queryForRowSet(sql, questionId);

        while (row.next()) {
            int answerId = row.getInt("answer_id");
            String answerTest = row.getString("answer_text");
            boolean isCorrect = row.getBoolean("is_correct");

            Answer answer = new Answer(answerId, questionId, answerTest, isCorrect);
            answers.add(answer);
        }
        return answers;
    }
}
