package com.ll.exam.sbb.controller;

import com.ll.exam.sbb.dto.AnswerForm;
import com.ll.exam.sbb.entity.Question;
import com.ll.exam.sbb.service.AnswerService;
import com.ll.exam.sbb.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@RequestMapping("/answer")
@Controller
@RequiredArgsConstructor
public class AnswerController {
    private final QuestionService questionService;
    private final AnswerService answerService;

    @PostMapping("/create/{id}")
    public String create(Model model, @PathVariable("id") int id, @Valid AnswerForm answerForm, BindingResult bindingResult) {
        Question question = questionService.getQuestion(id);

        if(bindingResult.hasErrors()) {
            model.addAttribute("question", question);
            return "question_detail";
        }

        //답변 등록 시작
        answerService.create(question, answerForm.getContent());


        //답변 등록 끝

        //답변 등록 후 다시 디테일 화면으로 돌아간다는 의미
        return "redirect:/question/detail/%d".formatted(id);
    }

}
