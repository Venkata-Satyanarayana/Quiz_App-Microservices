package com.satya.quiz_service.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;

    @ElementCollection
    private List<Integer> questionsIds;

    @Column(name = "username")
    private String username;

    public Quiz() {
    }

    public Quiz(int id, String title, List<Integer> questionsIds, String username) {
        this.id = id;
        this.title = title;
        this.questionsIds = questionsIds;
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Integer> getQuestionsIds() {
        return questionsIds;
    }

    public void setQuestionsIds(List<Integer> questionsIds) {
        this.questionsIds = questionsIds;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
