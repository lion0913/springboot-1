package com.ll.exam.sbb;

import com.ll.exam.sbb.entity.SiteUser;
import com.ll.exam.sbb.repository.AnswerRepository;
import com.ll.exam.sbb.repository.QuestionRepository;
import com.ll.exam.sbb.repository.UserRepository;
import com.ll.exam.sbb.service.UserService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AnswerRepository answerRepository;

    @BeforeEach
    @DisplayName("자동 생성 테스트")
    void before() {
        clearData();
        createTestData();

//        AnswerRepositoryTest.createSampleData(questionRepository, userRepository, answerRepository);
    }

    private void clearData() {


        answerRepository.deleteAll();
        answerRepository.truncate();

//        questionRepository.deleteAll();
//        questionRepository.truncate();

        userRepository.deleteAll();
        userRepository.truncate();
    }

    private void createTestData() {
        SiteUser siteUser1 = new SiteUser();
        siteUser1.setPassword("1234");
        siteUser1.setUsername("admin");
        siteUser1.setEmail("admin@test.com");

        userRepository.save(siteUser1);

        SiteUser siteUser2 = new SiteUser();
        siteUser2.setPassword("1234");
        siteUser2.setUsername("user1");
        siteUser2.setEmail("user1@test.com");

        userRepository.save(siteUser2);

        AnswerRepositoryTest.createSampleData(questionRepository, userRepository, answerRepository);
//        QuestionRepositoryTest.createSampleData(questionRepository, userRepository);
    }

    @Test
    void t1() {
//        AnswerRepositoryTest.t1();
    }

}
