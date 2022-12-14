package com.ll.exam.sbb.repository;

import com.ll.exam.sbb.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Integer>, RepositoryUtil {
    @Transactional
    @Modifying
    @Query(value = "ALTER TABLE answer AUTO_INCREMENT = 1", nativeQuery = true)
    void truncate();

}
