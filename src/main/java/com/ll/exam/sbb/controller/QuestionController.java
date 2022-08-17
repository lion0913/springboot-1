package com.ll.exam.sbb.controller;


import com.ll.exam.sbb.entity.Question;
import com.ll.exam.sbb.repository.QuestionRepository;
import com.ll.exam.sbb.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/question")
@Controller
@RequiredArgsConstructor
public class QuestionController
{
    private final QuestionService questionService;

    @GetMapping("/list")
    public String showList(Model model) {
        List<Question> questionList = questionService.findAll();

        // 미래에 실행된 question_list.html 에서
        // questionList 라는 이름으로 questionList 변수를 사용할 수 있다.
        model.addAttribute("questionList", questionList);

        return "question_list";
    }

    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable int id) {
        Question question = questionService.getQuestion(id);
        model.addAttribute("question", question);
        return "question_detail";
    }

    @GetMapping("/create")
    public String showCreateForm() {
        return "question_form";
    }

    @PostMapping("/create")
    public String create(@RequestParam String subject, String content) {
        questionService.create(subject, content);
        return "redirect:/question/list";
    }
}
