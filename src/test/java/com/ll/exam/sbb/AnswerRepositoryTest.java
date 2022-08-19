package com.ll.exam.sbb;


import com.ll.exam.sbb.entity.Answer;
import com.ll.exam.sbb.entity.Question;
import com.ll.exam.sbb.entity.SiteUser;
import com.ll.exam.sbb.repository.AnswerRepository;
import com.ll.exam.sbb.repository.QuestionRepository;
import com.ll.exam.sbb.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class AnswerRepositoryTest {
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private UserRepository userRepository;
    private int lastSampleDataId;

    @BeforeEach
    void beforeEach() {
        clearData();
        createSampleData();
    }

    private void clearData() {
        QuestionRepositoryTest.clearData(questionRepository);
        answerRepository.deleteAll(); // DELETE FROM question;
        answerRepository.truncateTable();
    }

    static void createSampleData(QuestionRepository questionRepository, UserRepository userRepository, AnswerRepository answerRepository) {
        QuestionRepositoryTest.createSampleData(questionRepository, userRepository);


        Question q = questionRepository.findById(1).get();
        SiteUser user = userRepository.findById(2).get();

        Answer a1 = new Answer();
        a1.setContent("sbb는 질문답변 게시판 입니다.");
        a1.setQuestion(q);
        a1.setCreateDate(LocalDateTime.now());
        a1.setAuthor(user);
        answerRepository.save(a1);

        Answer a2 = new Answer();
        a2.setContent("sbb에서는 주로 스프링부트관련 내용을 다룹니다.");
        a2.setQuestion(q);
        a2.setCreateDate(LocalDateTime.now());
        a2.setAuthor(user);
//        q.addAnswer(a1);
        answerRepository.save(a2);

//        questionRepository.save(q);
    }

    private void createSampleData() {
        createSampleData(questionRepository, userRepository, answerRepository);
    }



//    @Test
//    @Transactional
//    @Rollback(false)
//    void 관련된_question_조회() {
//        Answer a = this.answerRepository.findById(1).get();
//        Question q = a.getQuestion();
//
//        assertThat(q.getId()).isEqualTo(1);
//    }
//
//    @Test
//    @Transactional
//    @Rollback(false)
//    void question으로부터_관련된_질문들_조회() {
//        // SELECT * FROM question WHERE id = 1
//        Question q = questionRepository.findById(1).get();
//        // DB 연결이 끊김
//
//        // SELECT * FROM answer WHERE question_id = 1
//        List<Answer> answerList = q.getAnswerList();
//
//        assertThat(answerList.size()).isEqualTo(2);
//        assertThat(answerList.get(0).getContent()).isEqualTo("sbb는 질문답변 게시판 입니다.");
//    }
}
