package com.satya.quiz_service.controller;

import com.satya.quiz_service.model.QuestionWrapperAI;
import com.satya.quiz_service.model.QuizDTO;
import com.satya.quiz_service.model.Response;
import com.satya.quiz_service.model.ResponseListWrapper;
import com.satya.quiz_service.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class QuizAiController {

    @Autowired
    private QuizService quizService;

    @GetMapping("/quiz-ai")
    public String showQuizAIForm(@RequestParam String username, Model model) {
        model.addAttribute("quizDTO", new QuizDTO());
        model.addAttribute("quizCreated", false);
        model.addAttribute("username", username);
        return "create-quiz-ai";
    }

    @PostMapping("/createwAI")
    public String createQuizAI(@ModelAttribute("quizDTO") QuizDTO quizDTO,
                               @RequestParam("username") String username,
                               Model model) {

        String status = quizService.createQuizAi(quizDTO.getNumQuestions(), quizDTO.getCategory());

        if ("Created".equals(status)) {
            model.addAttribute("quizCreated", true);
        } else {
            model.addAttribute("ollamaFailed", true);
        }

        model.addAttribute("username", username);
        return "create-quiz-ai";
    }

    @GetMapping("/startQuizAI")
    public String startQuizAI(Model model) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        List<QuestionWrapperAI> questions = quizService.startQuizAi(username);
        model.addAttribute("questions", questions);
        model.addAttribute("responseWrapper", new ResponseListWrapper());
        model.addAttribute("username", username);

        return "start-quiz-ai";
    }

    @PostMapping("/submitAI")
    public String submitQuizAi(@ModelAttribute ResponseListWrapper responseWrapper, @RequestParam("username") String username,Model model) {
        List<Response> responses = responseWrapper.getResponses();
        int score = quizService.submitQuizAi(responses, username);
        System.out.println("score ; "+score);
        int total = responses.size();

        model.addAttribute("score", score);
        model.addAttribute("total", total);
        model.addAttribute("passed", score >= total / 2);

        return "quiz-result-ai";
    }
}
