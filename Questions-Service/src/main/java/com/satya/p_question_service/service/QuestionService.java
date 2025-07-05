package com.satya.p_question_service.service;

import com.satya.p_question_service.dao.QuestionDAO;
import com.satya.p_question_service.model.QuestionWrapper;
import com.satya.p_question_service.model.Questions;
import com.satya.p_question_service.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    @Autowired
    public QuestionDAO questionDao;
    public ResponseEntity<List<Questions>> getAllQuestions() {
       return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<List<Questions>> getQuestionsByCategory(String category) {
        List<Questions> questions=questionDao.findByCategory(category);
        return new ResponseEntity<>(questions,HttpStatus.OK);
    }

    public ResponseEntity<String> addQuestions(Questions questions) {
        questionDao.save(questions);
        return new ResponseEntity<>("success",HttpStatus.OK);
    }

    public ResponseEntity<List<Integer>> getQuestionIdsForQuiz(String category, int numQuestions) {
        List<Integer> questionIds= questionDao.findquestionIdsByRandom(category,numQuestions);
        return new ResponseEntity<>(questionIds,HttpStatus.OK);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromIds(List<Integer> questionIds) {
        List<QuestionWrapper> questionWrappers = new ArrayList<>();
        List<Questions> questions= new ArrayList<>();
        for(Integer q: questionIds){
            questions.add(questionDao.findById(q).get());
        }
        for(Questions q: questions){
            QuestionWrapper questionWrapper = new QuestionWrapper();
            questionWrapper.setId(q.getId());
            questionWrapper.setQuestion_title(q.getQuestion_title());
            questionWrapper.setOption1(q.getOption1());
            questionWrapper.setOption2(q.getOption2());
            questionWrapper.setOption3(q.getOption3());
            questionWrapper.setOption4(q.getOption4());
            questionWrappers.add(questionWrapper);
        }
         return  new ResponseEntity<>(questionWrappers,HttpStatus.OK);

    }

    public int getScore(List<Response> responses) {
        int right=0;
        for(Response r:responses){
            Questions questions = questionDao.findById(r.getId()).get();
            if(questions.getRight_answer().equals(r.getResponse())) {
                right++;
            }
        }
        return right;
    }
}
