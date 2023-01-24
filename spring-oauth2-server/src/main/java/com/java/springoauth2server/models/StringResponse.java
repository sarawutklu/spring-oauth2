package com.java.springoauth2server.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StringResponse {
    public StringResponse(String message){
        this.message = message;
    }
    private String message;
}
