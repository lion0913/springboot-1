package com.ll.exam.sbb.service;

import com.ll.exam.sbb.entity.Question;
import com.ll.exam.sbb.entity.SiteUser;
import com.ll.exam.sbb.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
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

    public void create(String subject, String content, SiteUser author) {
        Question question = new Question();
        question.setCreatedDate(LocalDateTime.now());
        question.setSubject(subject);
        question.setContent(content);
        question.setAuthor(author);

        questionRepository.save(question);
    }

    public Page<Question> getList(int page) {
//        정렬 조건넣기(생성일 순으로 정렬)
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("createdDate"));

        Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts)); // 한 페이지에 10까지 가능
        return questionRepository.findAll(pageable);
    }

    public void modify(Question question, String subject, String content) {
        question.setSubject(subject);
        question.setContent(content);
        question.setModifyDate(LocalDateTime.now());

        questionRepository.save(question);
    }
}
