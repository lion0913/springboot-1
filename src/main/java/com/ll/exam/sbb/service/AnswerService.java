package com.ll.exam.sbb.service;

import com.ll.exam.sbb.entity.Answer;
import com.ll.exam.sbb.entity.Question;
import com.ll.exam.sbb.entity.SiteUser;
import com.ll.exam.sbb.repository.AnswerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AnswerService {
    private final AnswerRepository answerRepository;


    public void create(Question question, String content, SiteUser author) {
        Answer answer = new Answer();
        answer.setContent(content);
        answer.setQuestion(question);
        answer.setCreateDate(LocalDateTime.now());
        answer.setAuthor(author);

        answerRepository.save(answer);
    }

    public Answer findById(int id) {
        return answerRepository.findById(id).orElse(null);
    }

    public void delete(Answer answer) {
        answerRepository.delete(answer);
    }

    public void modify(Answer answer, String content) {
        answer.setContent(content);
        answer.setModifyDate(LocalDateTime.now());

        answerRepository.save(answer);
    }

    public void vote(Answer answer, SiteUser siteUser) {
        answer.getVoter().add(siteUser);
        answerRepository.save(answer);
    }
}
