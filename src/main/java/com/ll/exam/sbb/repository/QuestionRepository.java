package com.ll.exam.sbb.repository;

import com.ll.exam.sbb.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
    Question findBySubjectAndContent(String subject, String content);
    Question findBySubject(String subject);

    List<Question> findByContentLike(String content);
}
