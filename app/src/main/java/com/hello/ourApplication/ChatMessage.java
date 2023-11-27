package com.hello.ourApplication;

public class ChatMessage {
    public boolean left; //left -> true: GPT, false: user
    public String message;

    public ChatMessage(boolean left, String message) {
        super();
        this.left = left;
        this.message = message;
    }
}