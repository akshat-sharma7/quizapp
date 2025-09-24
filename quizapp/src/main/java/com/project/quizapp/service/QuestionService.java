package com.project.quizapp.service;

import com.project.quizapp.dao.QuestionDao;
import com.project.quizapp.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<List<Question>> getAllQuestions() {
        try {
            return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Question>> getQuestionByCategory(String category) {
        try {
            return new ResponseEntity<>(questionDao.findByCategory(category), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> addQuestion(List<Question> question) {
        try {
            questionDao.saveAll(question);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
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
