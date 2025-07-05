package com.satya.p_question_service.controller;

import com.satya.p_question_service.model.QuestionWrapper;
import com.satya.p_question_service.model.Questions;
import com.satya.p_question_service.model.Response;
import com.satya.p_question_service.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/questions")
public class QuestionController {
    @Autowired
    public QuestionService questionService;

    @GetMapping("/all")
    public ResponseEntity<List<Questions>> getAllQuestions(){
       return  questionService.getAllQuestions();
    }
    @GetMapping("/category/{category}")
    public ResponseEntity<List<Questions>> getQuestionsByCategory(@PathVariable String category){
        return questionService.getQuestionsByCategory(category);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addQuestions(@RequestBody Questions questions){
        return  questionService.addQuestions(questions);
    }
    @GetMapping("/generate")
    public ResponseEntity<List<Integer>> getQuestionIdsForQuiz(@RequestParam String category,@RequestParam int numQuestions){
        return questionService.getQuestionIdsForQuiz(category,numQuestions);
    }
    @PostMapping("/getQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromIds(@RequestBody List<Integer> questionIds){
        return questionService.getQuestionsFromIds(questionIds);
    }

    @PostMapping("/getScore")
    public int getScore(@RequestBody List<Response> responses){
        return questionService.getScore(responses);
    }
}
