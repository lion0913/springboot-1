package com.ll.exam.sbb.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
public class Question {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Column(length=200)
    private String subject;

    @Column(columnDefinition = "TEXT")
    private String content;

    private LocalDateTime createdDate;
    private LocalDateTime modifyDate;

    @ManyToOne
    private SiteUser author;

    @OneToMany(mappedBy = "question",cascade = {CascadeType.ALL})
    private List<Answer> answerList;

    @ManyToMany
    Set<SiteUser> voter;

    public void addAnswer(Answer answer) {
        answer.setQuestion(this);
        getAnswerList().add(answer);
    }
}
