package com.telusko.quizapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.telusko.quizapp.dao.QuestionDao;
import com.telusko.quizapp.model.Question;

@Service
public class QuestionService {

	@Autowired
	QuestionDao questionDao;

	public ResponseEntity<List<Question>> getAllQuestion() {
		try {
			return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
	}

	public ResponseEntity<List<Question>> getQuestionByCategory(String category) {
		try {
			return new ResponseEntity<>(questionDao.findByCategory(category), HttpStatus.FOUND);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NOT_FOUND);
	}

	public ResponseEntity<String> addQuestion(Question question) {

		try {
			questionDao.save(question);
			return new ResponseEntity<>("success", HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>("not success", HttpStatus.NO_CONTENT);
	}

	public ResponseEntity<String> deleteQuestionById(Integer id) {
		try {
			questionDao.deleteById(id);
			return new ResponseEntity<>("deleted", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>("No Access to delete data", HttpStatus.UNAUTHORIZED);
	}

	public ResponseEntity<Question> updateQuestionById(Question question, Integer id) {
		try {
			return new ResponseEntity<>(questionDao.save(question), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(questionDao.save(question), HttpStatus.NOT_MODIFIED);
	}

	public ResponseEntity<Question> getQuestionsById(Integer id) {
		Optional<Question> question=questionDao.findById(id);
		
		if(question.isPresent()) {
		return new ResponseEntity<Question>(question.get(),HttpStatus.FOUND);
		}
		else return new ResponseEntity<Question>(HttpStatus.NOT_FOUND);
		}
}


