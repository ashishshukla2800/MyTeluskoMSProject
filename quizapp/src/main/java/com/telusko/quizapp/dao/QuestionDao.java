package com.telusko.quizapp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.telusko.quizapp.model.Question;

@Repository
public interface QuestionDao extends JpaRepository<Question, Integer>{
	
	List<Question> findByCategory(String category);

	@Query(value="select * from question q where q.category=:category order by rand() limit :numQ",nativeQuery = true)
	List<Question> findRandomQuestionsByCategory(String category, int numQ);

}
