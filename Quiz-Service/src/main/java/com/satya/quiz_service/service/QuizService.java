package com.satya.quiz_service.service;

import com.satya.quiz_service.dao.QuizDAO;
import com.satya.quiz_service.feignClient.QuizAiInterface;
import com.satya.quiz_service.feignClient.QuizInterface;
import com.satya.quiz_service.model.QuestionWrapper;
import com.satya.quiz_service.model.QuestionWrapperAI;
import com.satya.quiz_service.model.Quiz;
import com.satya.quiz_service.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizService {

    @Autowired
    private QuizDAO quizDAO;

    @Autowired
    private QuizInterface quizInterface;

    @Autowired
    private QuizAiInterface quizAiInterface;

    public ResponseEntity<String> createQuiz(String category, int numQuestions, String title) {
        String username = getCurrentUsername();

        List<Integer> questionIds = quizInterface.getQuestionIdsForQuiz(category, numQuestions).getBody();
        if (questionIds == null || questionIds.isEmpty()) {
            return new ResponseEntity<>("No questions received from question service", HttpStatus.BAD_REQUEST);
        }

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestionsIds(questionIds);
        quiz.setUsername(username);

        quizDAO.save(quiz);
        return new ResponseEntity<>("Quiz created successfully", HttpStatus.OK);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(int quizId) {
        Quiz quiz = quizDAO.findById(quizId).orElse(null);
        if (quiz == null || !quiz.getUsername().equals(getCurrentUsername())) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        List<Integer> questionIds = quiz.getQuestionsIds();
        List<QuestionWrapper> questions = quizInterface.getQuestionsFromIds(questionIds).getBody();
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    public int submitQuiz(List<Response> responses) {
        return quizInterface.getScore(responses);
    }

    public List<Quiz> getAllQuizzes() {
        System.out.println("Fetching quizzes for user: " + getCurrentUsername());
        return quizDAO.findByUsername(getCurrentUsername());
    }

    public String createQuizAi(int noOfQue, String language) {
        String username = getCurrentUsername();  // Always use current user
        System.out.println("Sending username to AI: [" + username + "]");
        quizAiInterface.saveQue(noOfQue, language, username);
         return "Created";
    }

    public List<QuestionWrapperAI> startQuizAi(String username) {
        return quizAiInterface.getQuestions(username);
    }

    public int submitQuizAi(List<Response> responses, String username) {
        return quizAiInterface.submitQue(responses, username);
    }

    private String getCurrentUsername() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth != null ? auth.getName() : "anonymous";
    }
}
