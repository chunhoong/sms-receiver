package com.chunhoong.smsreceiverserver.rest;

public class IncomingSmsRequest {

    private String content;
    private String sender;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    @Override
    public String toString() {
        return "IncomingSms{" +
                "content='" + content + '\'' +
                ", sender='" + sender + '\'' +
                '}';
    }

}
