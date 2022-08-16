package com.ll.exam.sbb.controller;

import com.ll.exam.sbb.entity.Question;
import com.ll.exam.sbb.service.AnswerService;
import com.ll.exam.sbb.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/answer")
@Controller
@RequiredArgsConstructor
public class AnswerController {
    private final QuestionService questionService;
    private final AnswerService answerService;

    @PostMapping("/create/{id}")
    public String create(Model model, @PathVariable("id") int id, String content) {
        Question question = questionService.getQuestion(id);
        System.out.println("id : "+id);

        //답변 등록 시작
        answerService.create(question, content);


        //답변 등록 끝

        //답변 등록 후 다시 디테일 화면으로 돌아간다는 의미
        return "redirect:/question/detail/%d".formatted(id);
    }
}
