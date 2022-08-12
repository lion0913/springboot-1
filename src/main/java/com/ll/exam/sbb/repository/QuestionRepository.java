package com.ll.exam.sbb.repository;

import com.ll.exam.sbb.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {
    Question findBySubjectAndContent(String subject, String content);
    Question findBySubject(String subject);

    List<Question> findByContentLike(String content);
    @Transactional
    @Modifying
    @Query(value = "TRUNCATE table question", nativeQuery = true)
    void truncateQuestion();

    @Transactional
    @Modifying
    @Query(value = "truncate question", nativeQuery = true)
    void truncate();

    @Transactional
    @Modifying
    @Query(value = "SET FOREIGN_KEY_CHECKS = 0", nativeQuery = true)
    void disableForeignKeyChecks();

    @Transactional
    @Modifying
    @Query(value = "SET FOREIGN_KEY_CHECKS = 1", nativeQuery = true)
    void enableForeignKeyChecks();

    List<Question> findBySubjectLike(String s);
}
