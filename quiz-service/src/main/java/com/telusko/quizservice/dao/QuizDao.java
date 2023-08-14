package com.telusko.quizservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.telusko.quizservice.model.Quiz;

@Repository
public interface QuizDao extends JpaRepository<Quiz, Integer> {

}
