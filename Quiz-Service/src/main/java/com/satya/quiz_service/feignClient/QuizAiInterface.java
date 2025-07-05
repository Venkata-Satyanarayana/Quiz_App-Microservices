package com.satya.quiz_service.feignClient;

import com.satya.quiz_service.model.QuestionWrapperAI;
import com.satya.quiz_service.model.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "QUESTIONS-AI")
public interface QuizAiInterface {

    @PostMapping("/savequestion")
    String saveQue(@RequestParam int noQuestions,
                   @RequestParam String language,
                   @RequestParam String username);

    @GetMapping("/getQuestions")
    List<QuestionWrapperAI> getQuestions(@RequestParam String username);

    @PostMapping("/Submit")
    int submitQue(@RequestBody List<Response> responses, @RequestParam  String username);
}

