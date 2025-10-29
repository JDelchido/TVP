package com.TVP.Turismo.DTO;

import java.io.Serializable;

public class EmailMessage implements Serializable {
    private static final long serialVersionUID = 1L;

    private String to;
    private String subject;
    private String body;
    private String from;

    public EmailMessage() {}
    public EmailMessage(String to, String subject, String body, String from) {
        this.to = to; this.subject = subject; this.body = body; this.from = from;
    }
    public String getTo() { return to; }
    public String getSubject() { return subject; }
    public String getBody() { return body; }
    public String getFrom() { return from; }
    public void setTo(String to) { this.to = to; }
    public void setSubject(String subject) { this.subject = subject; }
    public void setBody(String body) { this.body = body; }
    public void setFrom(String from) { this.from = from; }
}
