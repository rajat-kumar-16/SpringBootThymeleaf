package com.example.demo.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.models.tutorial;

import jakarta.transaction.Transactional;

import java.util.*;

@Repository
@Transactional	
public interface TutorialRepo extends JpaRepository<tutorial, Integer> {
	List<tutorial> findByTitleContainingIgnoreCase(String keyword);
	@Modifying
	@Query(value ="UPDATE tutorial SET published = :published WHERE id = :id" ,nativeQuery = true)
	  public void updatePublishedStatus(@Param("id") Integer id, @Param("published") boolean published);
}
