package com.ll.exam.sbb.entity;

import lombok.Data;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@Entity
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "TEXT")
    private String content;

    private LocalDateTime createDate;
    private LocalDateTime modifyDate;
    @ManyToOne
    private SiteUser author;

    @ManyToMany
    Set<SiteUser> voter;

    @ManyToOne
    private Question question;
}
