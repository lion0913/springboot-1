package com.ll.exam.sbb.repository;

import com.ll.exam.sbb.entity.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer>, RepositoryUtil {
    Question findBySubjectAndContent(String subject, String content);
    Question findBySubject(String subject);

    List<Question> findByContentLike(String content);
    @Transactional
    @Modifying
    @Query(value = "TRUNCATE table question", nativeQuery = true)
    void truncateQuestion();

    @Transactional
    @Modifying
    @Query(value = "ALTER TABLE question AUTO_INCREMENT = 1", nativeQuery = true)
    void truncate();


//    @Query("select q from Question as q where q.content like %:kw% or q.subject like %:kw%  ")
//    Page<Question> findByKeyword(Pageable pageable, @Param("kw") String kw);


    Page<Question> findBySubjectContains(String kw, Pageable pageable);
}
