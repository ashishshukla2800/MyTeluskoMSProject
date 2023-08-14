package com.telusko.questionservice.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.telusko.questionservice.model.Question;

@Repository
public interface QuestionDao extends JpaRepository<Question, Integer>{
	
	List<Question> findByCategory(String category);

	@Query(value="select id from question q where q.category=:category order by rand() limit :numQ",nativeQuery = true)
	List<Integer> findRandomQuestionsByCategory(String category, int numQ);

}
