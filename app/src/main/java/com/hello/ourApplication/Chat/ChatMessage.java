package com.hello.ourApplication.Chat;

public class ChatMessage {
    public static String SENT_BY_ME = "me";
    public static String SENT_BY_BOT = "bot";

    private String message;
    private String sentBy;

    public ChatMessage(String message, String sentBy) {
        this.message = message;
        this.sentBy = sentBy;
    }

    public ChatMessage() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSentBy() {
        return sentBy;
    }

    public void setSentBy(String sentBy) {
        this.sentBy = sentBy;
    }
}