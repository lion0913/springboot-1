package com.ll.exam.sbb;

import com.ll.exam.sbb.entity.Question;
import com.ll.exam.sbb.entity.SiteUser;
import com.ll.exam.sbb.repository.QuestionRepository;
import com.ll.exam.sbb.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class QuestionRepositoryTest {
    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private UserRepository userRepository;


    @BeforeEach
    void beforeEach() {
        clearData();
        createSampleData();
    }

     static void createSampleData(QuestionRepository questionRepository, UserRepository userRepository) {
        SiteUser user = userRepository.findById(2).orElse(null);

        if(user == null) return;

        Question q1 = new Question();
        q1.setSubject("sbb가 무엇인가요?");
        q1.setContent("sbb에 대해서 알고 싶습니다.");
        q1.setCreatedDate(LocalDateTime.now());
        q1.setAuthor(user);
        questionRepository.save(q1);

        Question q2 = new Question();
        q2.setSubject("스프링부트 모델 질문입니다.");
        q2.setContent("id는 자동으로 생성되나요?");
        q2.setCreatedDate(LocalDateTime.now());
        q2.setAuthor(user);
        questionRepository.save(q2);

        IntStream.rangeClosed(3,300).forEach(i -> {
            Question q = new Question();
            q.setSubject("%d번 질문".formatted(i));
            q.setContent("%d번 질문의 내용".formatted(i));
            q.setCreatedDate(LocalDateTime.now());
            q.setAuthor(user);

            questionRepository.save(q);
        });

    }

    void createSampleData() {
        createSampleData(questionRepository, userRepository);
    }

    static void clearData(QuestionRepository questionRepository) {
        questionRepository.deleteAll(); // DELETE FROM question;
        questionRepository.truncateTable();
    }

    void clearData() {
        clearData(questionRepository);
    }

    @Test
    void t1() {

    }

}
