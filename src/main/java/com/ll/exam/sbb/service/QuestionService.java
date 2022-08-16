package com.ll.exam.sbb.service;

import com.ll.exam.sbb.entity.Question;
import com.ll.exam.sbb.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor //final 붙은 것에 대한 생성자가 만들어짐
public class QuestionService {
    private final QuestionRepository questionRepository;

//    public QuestionService(QuestionRepository questionRepository) {
//        this.questionRepository = questionRepository;
//    }

    public Question findById(int id) {
        Question q1 = questionRepository.findById(2).get();
        Question q2 = questionRepository.findById(2).get();
        System.out.println(q2.getAnswerList());

        return q2;
    }

    public List<Question> findAll() {
        return questionRepository.findAll();
    }

    public Question getQuestion(int id) {
        Question q = questionRepository.findById(id).orElse(null);

        return q;
    }
}
