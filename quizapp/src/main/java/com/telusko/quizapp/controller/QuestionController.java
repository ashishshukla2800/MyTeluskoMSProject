package com.telusko.quizapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.telusko.quizapp.model.Question;
import com.telusko.quizapp.service.QuestionService;

import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
@RequestMapping("/question")
public class QuestionController {

	@Autowired
	QuestionService questionService;

	@GetMapping("/allQuestions")
	public ResponseEntity<List<Question>> getAllQuestions() {

		log.info("An INFO Message" + questionService.getAllQuestion());
		log.error("An ERROR Message" + questionService.getAllQuestion());
		return questionService.getAllQuestion();
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<Question> getElementsById(@PathVariable Integer id){
		return questionService.getQuestionsById(id);
	}

	@GetMapping("/category/{category}")
	public ResponseEntity<List<Question>> getQuestionByCategory(@PathVariable String category) {

		return questionService.getQuestionByCategory(category);
	}

	@PostMapping("/add")
	public ResponseEntity<String> addQuestion(@RequestBody Question question) {

		log.info("An INFO Message" + questionService.addQuestion(question));
		return questionService.addQuestion(question);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteQuestion(@PathVariable Integer id) {

		log.info("An INFO Message" + questionService.deleteQuestionById(id));
		return questionService.deleteQuestionById(id);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<Question> updateQuestion(@RequestBody Question question, @PathVariable Integer id) {
		log.info("An INFO Message updated:" + questionService.updateQuestionById(question, id));
		return questionService.updateQuestionById(question, id);

	}

}
