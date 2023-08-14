package com.telusko.quizservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

//import com.telusko.quizservice.dao.QuestionDao;
import com.telusko.quizservice.dao.QuizDao;
import com.telusko.quizservice.feign.QuizInterface;
//import com.telusko.quizservice.model.Question;
import com.telusko.quizservice.model.QuestionWrapper;
import com.telusko.quizservice.model.Quiz;
import com.telusko.quizservice.model.Response;

@Service
public class QuizService {

	@Autowired
	QuizDao quizDao;

	@Autowired
	QuizInterface quizInterface;

	public ResponseEntity<String> createQuiz(String category, int numQ, String title) {

		// List<Integer>questions=//call the generate url - RestTemplate
		// http://localhost:8081/question/generate ->hrdcoded host/port
		// OR feign
		List<Integer> questions = quizInterface.getQuestionsForQuiz(category, numQ).getBody();

		Quiz quiz = new Quiz();
		quiz.setTitle(title);
		quiz.setQuestionIds(questions);

		quizDao.save(quiz);

		return new ResponseEntity<>("SUCCESS", HttpStatus.CREATED);
	}

	public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
		
		 Quiz quiz=quizDao.findById(id).get(); 
	     List<Integer>questionsIds=quiz.getQuestionIds();	 
		 
	     ResponseEntity<List<QuestionWrapper>> questions = quizInterface.getQuestionsFromId(questionsIds);
		
	     return questions;
		
	}

	public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
        quizInterface.getScore(responses);
		ResponseEntity<Integer> score=quizInterface.getScore(responses);
		return score;
	}

}
