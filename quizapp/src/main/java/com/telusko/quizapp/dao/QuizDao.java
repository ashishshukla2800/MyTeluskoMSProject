package com.telusko.quizapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.telusko.quizapp.model.Quiz;

@Repository
public interface QuizDao extends JpaRepository<Quiz, Integer> {

}
