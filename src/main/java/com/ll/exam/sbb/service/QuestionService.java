package com.ll.exam.sbb.service;

import com.ll.exam.sbb.entity.Question;
import com.ll.exam.sbb.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor //final 붙은 것에 대한 생성자가 만들어짐
public class QuestionService {
    private final QuestionRepository questionRepository;


    public Question getQuestion(int id) {
        Question q = questionRepository.findById(id).orElse(null);

        return q;
    }

    public void create(String subject, String content) {
        Question question = new Question();
        question.setCreatedDate(LocalDateTime.now());
        question.setSubject(subject);
        question.setContent(content);

        questionRepository.save(question);
    }

    public Page<Question> getList(int page) {
        Pageable pageable = PageRequest.of(page, 10); // 한 페이지에 10까지 가능
        return questionRepository.findAll(pageable);
    }
}
