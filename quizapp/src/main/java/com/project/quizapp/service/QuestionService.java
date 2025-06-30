package com.project.quizapp.service;

import com.project.quizapp.dao.QuestionDao;
import com.project.quizapp.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {

    @Autowired
    QuestionDao questionDao;

    public List<Question> getAllQuestions() {
        return questionDao.findAll();
    }

    public List<Question> getQuestionByCategory(String category) {
        return questionDao.findByCategory(category);
    }

    public String addQuestion(List<Question> question) {
        questionDao.saveAll(question);
        return "success";
    }

    public void deleteQuestion(Integer id) {
        questionDao.deleteById(id);
    }

    public Question updateQuestion(Integer id, String difficultyLevel) {
        return questionDao.findById(id)
                .map(question -> {
                    question.setDifficultylevel(difficultyLevel);
                    return questionDao.save(question);
                })
                .orElseThrow(() -> new RuntimeException("Question not found with id: " + id));
    }
}
