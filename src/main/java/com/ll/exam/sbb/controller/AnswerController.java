package com.ll.exam.sbb.controller;

import com.ll.exam.sbb.dto.AnswerForm;
import com.ll.exam.sbb.entity.Answer;
import com.ll.exam.sbb.entity.Question;
import com.ll.exam.sbb.entity.SiteUser;
import com.ll.exam.sbb.service.AnswerService;
import com.ll.exam.sbb.service.QuestionService;
import com.ll.exam.sbb.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.security.Principal;

@RequestMapping("/answer")
@Controller
@RequiredArgsConstructor
public class AnswerController {
    private final QuestionService questionService;
    private final AnswerService answerService;

    private final UserService userService;

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/create/{id}")
    public String create(Principal principal, Model model, @PathVariable("id") int id, @Valid AnswerForm answerForm, BindingResult bindingResult) {
        Question question = questionService.getQuestion(id);

        if(bindingResult.hasErrors()) {
            model.addAttribute("question", question);
            return "question_detail";
        }

        SiteUser siteUser = userService.getUser(principal.getName());

        //답변 등록 시작
        Answer answer = answerService.create(question, answerForm.getContent(), siteUser);


        //답변 등록 끝

        //답변 등록 후 다시 디테일 화면으로 돌아간다는 의미
        return "redirect:/question/detail/%d#answer_%d".formatted(id, answer.getId());
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/delete/{id}")
    public String delete(Principal principal, Model model, @PathVariable int id) {
        Answer answer = answerService.findById(id);

        if(!principal.getName().equals(answer.getAuthor().getUsername())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "댓글 삭제권한이 없습니다.");
        }

        answerService.delete(answer);

        return "redirect:/question/detail/%d".formatted(answer.getQuestion().getId());
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/modify/{id}")
    public String modifyForm(AnswerForm answerForm, Principal principal, Model model, @PathVariable int id) {
        Answer answer = answerService.findById(id);

        if(!principal.getName().equals(answer.getAuthor().getUsername())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "댓글 수정 권한이 없습니다.");
        }

        answerForm.setContent(answer.getContent());

        return "answer_form";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/modify/{id}")
    public String modifyAnswer(@Valid AnswerForm answerForm, BindingResult bindingResult, @PathVariable("id") Integer id, Principal principal) {
        if(bindingResult.hasErrors()){
            return "answer_form";
        }

        Answer answer = answerService.findById(id);
        if (!answer.getAuthor().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        answerService.modify(answer, answerForm.getContent());
        return String.format("redirect:/question/detail/%s", answer.getQuestion().getId());
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/vote/{id}")
    public String voteAnswer(Principal principal, @PathVariable("id") int id) {
        Answer answer = answerService.findById(id);

        SiteUser siteUser = userService.getUser(principal.getName());

        answerService.vote(answer, siteUser);

        return "redirect:/question/detail/%s#answer_%d".formatted(answer.getQuestion().getId(), id);
    }

}
