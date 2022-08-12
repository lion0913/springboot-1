package com.ll.exam.sbb;

import com.ll.exam.sbb.entity.Question;
import com.ll.exam.sbb.repository.QuestionRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class SbbApplicationTests {

	@Autowired
	private  QuestionRepository questionRepository;


	@Test
	void testTruncate() {
		questionRepository.truncateQuestion();
	}

	@Test
	void testJpa() {
		Question q1 = new Question();
		q1.setSubject("q1");
		q1.setContent("질문1");
		q1.setCreatedDate(LocalDateTime.now());
		this.questionRepository.save(q1);

		Question q2 = new Question();
		q2.setSubject("q2");
		q2.setContent("질문2");
		q2.setCreatedDate(LocalDateTime.now());
		this.questionRepository.save(q2);

		assertThat(q1.getId()).isGreaterThan(0);
	}

	@Test
	void testJpa2() {
		List<Question> all = questionRepository.findAll();
		assertEquals(2, all.size());

		Question q = all.get(0);
		assertEquals("q1", q.getSubject());
	}

	@Test
	void findByIdTest() {
//		findById 구
		Question question = questionRepository.findById(0).orElse(null);
		if(question != null) {
			assertThat(question.getContent()).isEqualTo("질문1");
		}
	}

	@Test
	void findByTest() {
		Question q = questionRepository.findBySubjectAndContent("q1", "질문1");
		assertEquals(0, q.getId());
	}

	@Test
	void findByLikeTest() {
		List<Question> questionList = questionRepository.findByContentLike("질문%");

		Question q = questionList.get(0);
		assertEquals("q1", q.getSubject());
	}

	@Test
	void modifyTest() {
		Optional<Question> oq = questionRepository.findById(1);
		assertTrue(oq.isPresent());
		Question q = oq.get();
		q.setSubject("수정된 제목");
		questionRepository.save(q);
	}

	@Test
	void deleteTest() {
		assertEquals(2, this.questionRepository.count());
		Optional<Question> oq = this.questionRepository.findById(1);
		assertTrue(oq.isPresent());
		Question q = oq.get();
		this.questionRepository.delete(q);
		assertEquals(1, this.questionRepository.count());
	}
}
