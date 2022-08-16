package com.ll.exam.sbb.service;

import com.ll.exam.sbb.entity.Answer;
import com.ll.exam.sbb.entity.Question;
import com.ll.exam.sbb.repository.AnswerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AnswerService {
    private final AnswerRepository answerRepository;


    public void create(Question question, String content) {
        Answer answer = new Answer();
        answer.setContent(content);
        answer.setQuestion(question);
        answer.setCreateDate(LocalDateTime.now());

        answerRepository.save(answer);
    }
}
