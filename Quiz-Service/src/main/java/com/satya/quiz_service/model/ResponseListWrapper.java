package com.satya.quiz_service.model;


import lombok.Data;

import java.util.List;

@Data
public class ResponseListWrapper {
    private List<Response> responses;

    public ResponseListWrapper() {
        this.responses = responses;
    }

    public List<Response> getResponses() {
        return responses;
    }

    public void setResponses(List<Response> responses) {
        this.responses = responses;
    }
}

