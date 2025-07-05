package com.satya.quiz_service.controller;

import com.satya.quiz_service.model.QuizDTO;
import com.satya.quiz_service.model.QuestionWrapper;
import com.satya.quiz_service.model.Response;
import com.satya.quiz_service.model.ResponseListWrapper;
import com.satya.quiz_service.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class QuizController {

    @Autowired
    public QuizService quizService;

    @GetMapping("/home")
    public String showHomePage() {
        return "home";
    }

    @GetMapping("/quiz")
    public String showQuizPage(Model model) {
        model.addAttribute("quizDTO", new QuizDTO());
        model.addAttribute("quizzes", quizService.getAllQuizzes());
        return "quiz";
    }

    @PostMapping("/create")
    public String createQuiz(@ModelAttribute QuizDTO quizDTO, Model model) {
        quizService.createQuiz(quizDTO.getCategory(), quizDTO.getNumQuestions(), quizDTO.getTitle());
        return "redirect:/quiz";
    }

    @GetMapping("/startQuiz")
    public String startQuiz(@RequestParam int quizId, Model model) {
        ResponseEntity<List<QuestionWrapper>> response = quizService.getQuizQuestions(quizId);
        if (response.getStatusCode().is4xxClientError()) {
            return "error";
        }
        model.addAttribute("questions", response.getBody());
        return "start-quiz";
    }

    @PostMapping("/submit")
    public String submitQuiz(@ModelAttribute ResponseListWrapper responseWrapper, Model model) {
        List<Response> responses = responseWrapper.getResponses();
        int score = quizService.submitQuiz(responses);
        int total = responses.size();

        model.addAttribute("score", score);
        model.addAttribute("total", total);
        model.addAttribute("passed", score >= total / 2);

        return "quiz-result";
    }
}
