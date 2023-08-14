package com.telusko.questionservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.telusko.questionservice.dao.QuestionDao;
import com.telusko.questionservice.model.Question;
import com.telusko.questionservice.model.QuestionWrapper;
import com.telusko.questionservice.model.Response;

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
		Optional<Question> question = questionDao.findById(id);

		if (question.isPresent()) {
			return new ResponseEntity<Question>(question.get(), HttpStatus.FOUND);
		} else
			return new ResponseEntity<Question>(HttpStatus.NOT_FOUND);
	}

	public ResponseEntity<List<Integer>> getQuestionForQuiz(String category, Integer noOfQuestions) {

			List<Integer> questions = questionDao.findRandomQuestionsByCategory(category, noOfQuestions);

			return new ResponseEntity<>(questions, HttpStatus.OK);
		
	}

	public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(List<Integer> questionIds) {
		
		List<QuestionWrapper> wrappers=new ArrayList<>();
		
		List<Question> questions=new ArrayList<>();
		
		for(Integer id :  questionIds) {
			questions.add(questionDao.findById(id).get());
			
		}
		
		for(Question question : questions) {
			QuestionWrapper wrapper=new QuestionWrapper();
			wrapper.setId(question.getId());
			wrapper.setQuestionTitle(question.getQuestionTitle());
			wrapper.setOption1(question.getOption1());
			wrapper.setOption2(question.getOption2());
			wrapper.setOption3(question.getOption3());
			wrapper.setOption4(question.getOption4());
			wrappers.add(wrapper);
		}
		return new ResponseEntity<List<QuestionWrapper>>(wrappers,HttpStatus.OK);
	}

	public ResponseEntity<Integer> getScore(List<Response> responses) {
		
		int result=0;
		for(Response response:responses) {
			Question question=questionDao.findById(response.getId()).get();
			if(response.getResponse().equals(question.getRightAnswer()))
				result++;
		        
		}
		
		return new ResponseEntity<Integer>(result,HttpStatus.OK);
	}
}
