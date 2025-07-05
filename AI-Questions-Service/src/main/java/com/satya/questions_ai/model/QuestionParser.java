package com.satya.questions_ai.model;

import java.util.ArrayList;
import java.util.List;

public class QuestionParser {

    public static List<Questions> parseMultiple(String input) {
        List<Questions> questionsList = new ArrayList<>();
        String[] blocks = input.split("(?=Q:)");

        for (String block : blocks) {
            if (block.trim().isEmpty()) continue;

            Questions q = new Questions();
            String[] lines = block.trim().split("\n");

            for (String line : lines) {
                line = line.trim();
                if (line.startsWith("Q:")) {
                    q.setQuestion(line.substring(2).trim());
                } else if (line.startsWith("A)")) {
                    q.setOption1(line.substring(2).trim());
                } else if (line.startsWith("B)")) {
                    q.setOption2(line.substring(2).trim());
                } else if (line.startsWith("C)")) {
                    q.setOption3(line.substring(2).trim());
                } else if (line.startsWith("D)")) {
                    q.setOption4(line.substring(2).trim());
                } else if (line.toLowerCase().startsWith("answer:")) {
                    String rawAnswer = line.substring(7).trim(); // e.g., B) String
                    int closeParenIndex = rawAnswer.indexOf(")");
                    if (closeParenIndex != -1 && closeParenIndex + 1 < rawAnswer.length()) {
                        String answerText = rawAnswer.substring(closeParenIndex + 1).trim();
                        q.setAnswer(answerText);
                    }
                }
            }

            // Add only if all required fields are present
            if (q.getQuestion() != null &&
                    q.getOption1() != null &&
                    q.getOption2() != null &&
                    q.getOption3() != null &&
                    q.getOption4() != null &&
                    q.getAnswer() != null) {
                questionsList.add(q);
            } else {
                System.out.println("⚠️ Incomplete question skipped:\n" + block);
            }
        }

        return questionsList;
    }
}
