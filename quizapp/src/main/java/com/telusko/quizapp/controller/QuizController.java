package com.telusko.quizapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.telusko.quizapp.model.QuestionWrapper;
import com.telusko.quizapp.model.Response;
import com.telusko.quizapp.service.QuizService;

import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
@RequestMapping("/quiz")
public class QuizController {

	@Autowired
	QuizService quizService;

	@PostMapping("/create")
	public ResponseEntity<String> ceateQuiz(@RequestParam String category, @RequestParam int numQ,
			@RequestParam String title) {
		log.info("An INFO Message" + quizService.createQuiz(category, numQ, title));
		return quizService.createQuiz(category, numQ, title);
	}

	@GetMapping("/get/{id}")
	public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable("id") Integer i) {
		log.info("An INFO Message" + quizService.getQuizQuestions(i));
		ResponseEntity<List<QuestionWrapper>> res = quizService.getQuizQuestions(i);
		return res;
	}

	@PostMapping("/submit/{id}")
	public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id, @RequestBody List<Response> responses) {
		return quizService.calculateResult(id, responses);
	}
}
