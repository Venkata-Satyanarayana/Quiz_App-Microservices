package com.satya.quiz_service.model;

public class Response {
    private int id;
    private String response;

    public Response() {
    }

    public Response(String response, int id) {
        this.response = response;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
