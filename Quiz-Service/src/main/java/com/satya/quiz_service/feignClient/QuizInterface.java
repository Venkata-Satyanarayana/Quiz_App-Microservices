package com.satya.quiz_service.feignClient;

import com.satya.quiz_service.model.QuestionWrapper;
import com.satya.quiz_service.model.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("P-QUESTION-SERVICE")
public interface QuizInterface {

    @GetMapping("/questions/generate")
    public ResponseEntity<List<Integer>> getQuestionIdsForQuiz(@RequestParam String category, @RequestParam int numQuestions);

    @PostMapping("/questions/getQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromIds(@RequestBody List<Integer> questionIds);

    @PostMapping("/questions/getScore")
    public int getScore(@RequestBody List<Response> responses);
}
