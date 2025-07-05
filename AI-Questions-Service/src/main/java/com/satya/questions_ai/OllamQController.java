package com.satya.questions_ai;

import com.satya.questions_ai.model.QuestionParser;
import com.satya.questions_ai.model.QuestionWrapper;
import com.satya.questions_ai.model.Questions;
import com.satya.questions_ai.model.Response;
import com.satya.questions_ai.repo.QueRepo;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class OllamQController {
    private OllamaChatModel chatModel;
    @Autowired
    private QueRepo queRepo;

    public OllamQController(OllamaChatModel chatModel) {
        this.chatModel = chatModel;
    }

    @PostMapping("/savequestion")
    @Transactional
    public String saveQue(@RequestParam int noQuestions,
                          @RequestParam String language,
                          @RequestParam String username) {
        System.out.println("Username received: " + username);

        String prompt = String.format("""
        Generate exactly %d multiple-choice questions (MCQs) on %s programming language.
        Use the following format exactly:

        Q: <Question>
        A) <Option 1>
        B) <Option 2>
        C) <Option 3>
        D) <Option 4>
        Answer: <Correct answer>
        instructions:
        dont give any extra text at any cost
        give how many questions i asked for-recheck how many questions youre giving
        Only return questions and answers in this format. No extra text.
        """, noQuestions, language);

        System.out.println("Prompt: " + prompt);

        String response = chatModel.call(prompt);
        System.out.println("AI Response:\n" + response);

        List<Questions> questions = QuestionParser.parseMultiple(response);
        System.out.println("Parsed Questions Count: " + questions.size());

        if(questions.size()==noQuestions){
        for (Questions q : questions) {
            q.setUsername(username);
            System.out.println("Saving question for user: " + username);
        }

        queRepo.saveAll(questions);
        System.out.println("Saved to DB.");
        return "saved";
        }else {
            return "unable to fetch from AI";
        }
    }

    @GetMapping("/getQuestions")
    public List<QuestionWrapper> getQuestions(@RequestParam String username){
       List<Questions>questions= queRepo.findByUsername(username);
       List<QuestionWrapper> questionWrappers=new ArrayList<>();

       for(Questions q:questions) {
           QuestionWrapper questionWrapper=new QuestionWrapper();
           questionWrapper.setId(q.getId());
           questionWrapper.setQuestion(q.getQuestion());
           questionWrapper.setOption1(q.getOption1());
           questionWrapper.setOption2(q.getOption2());
           questionWrapper.setOption3(q.getOption3());
           questionWrapper.setOption4(q.getOption4());
           questionWrappers.add(questionWrapper);
       }
       return questionWrappers;
   }

    @Transactional
    @PostMapping("/Submit")
    public int submitQue(@RequestBody List<Response> responses, @RequestParam String username) {
        int right = 0;

        for (Response r : responses) {
            Questions questions = queRepo.findById(r.getId()).orElse(null);
            if (questions != null && questions.getAnswer() != null && r.getResponse() != null) {
                if (questions.getAnswer().trim().equalsIgnoreCase(r.getResponse().trim())) {
                    right++;
                }
            }
        }


        queRepo.deleteByUsername(username);
        queRepo.resetAutoIncrement();

        return right;
    }

}
