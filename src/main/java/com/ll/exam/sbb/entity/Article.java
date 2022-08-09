package com.ll.exam.sbb.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Article {
    private static int totalId = 0;
    private int id;
    private String title;
    private String body;

    public Article(String title, String body) {
        this(++totalId, title, body);
    }
}
