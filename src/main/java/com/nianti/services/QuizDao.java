package com.nianti.services;

import com.nianti.models.Quiz;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class QuizDao
{
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public QuizDao()
    {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/trivio");
        dataSource.setUsername("root");
        dataSource.setPassword("P@ssw0rd");

        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<Quiz> getAllQuizzes()
    {
        ArrayList<Quiz> quizzes = new ArrayList<>();
        String sql = """
            SELECT quiz_id
                , quiz_title
                , is_live
            FROM quiz;
        """;
        var row = jdbcTemplate.queryForRowSet(sql);
        while (row.next())
        {
            var quiz = mapRowToQuiz(row);
            quizzes.add(quiz);
        }

        return quizzes;
    }

    private Quiz mapRowToQuiz(SqlRowSet row)
    {
        int id = row.getInt("quiz_id");
        String title = row.getString("quiz_title");
        boolean isLive = row.getBoolean("is_live");

        return new Quiz(id, title, isLive);
    }

    public Quiz getQuizById(int id)
    {
        String sql = """
               SELECT quiz_id
                        ,quiz_title
                        ,is_live
               FROM quiz
               WHERE quiz_id = ?;
               """;
        var row = jdbcTemplate.queryForRowSet(sql, id);

        if(row.next())
        {
            int quizId = row.getInt("quiz_id");
            String quizTitle = row.getString("quiz_title");
            boolean isLive = row.getBoolean("is_live");


            return new Quiz(quizId, quizTitle, isLive);
        }

        return null;
    }
    public Quiz getQuizByTitle(String name)
    {
        String sql = """
               SELECT quiz_id
                        ,quiz_title
                        ,is_live
               FROM quiz
               WHERE quiz_title = ?;
               """;
        var row = jdbcTemplate.queryForRowSet(sql, name);

        if(row.next())
        {
            int quizId = row.getInt("quiz_id");
            String quizTitle = row.getString("quiz_title");
            boolean isLive = row.getBoolean("is_live");


            return new Quiz(quizId, quizTitle, isLive);
        }

        return null;
    }
    public void addQuiz(Quiz quiz) {
        String sql = """
                INSERT INTO quiz (quiz_id, quiz_title, is_live)
                VALUES ( ?,?,?);
                """;
        jdbcTemplate.update(sql
        ,quiz.getQuizId()
        ,quiz.getTitle()
        ,quiz.isLive());
    }
    public void updateQuiz(Quiz quiz) {
        String sql = """
            UPDATE quiz
            SET quiz_title = ?
                , is_live = ?
            WHERE quiz_id = ?;
            """;
        jdbcTemplate.update(sql
                    ,quiz.getTitle()
                    ,quiz.isLive() ? 1 : 0
                    ,quiz.getQuizId());
    }
    public void deleteQuiz(int quizId)
    {
        String sql = """
                DELETE FROM quiz
                WHERE quiz_id = ?
                """;

        jdbcTemplate.update(sql, quizId);
    }
}
