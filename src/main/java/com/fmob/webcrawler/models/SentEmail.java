package com.fmob.webcrawler.models;

import javax.persistence.*;

@Entity
@Table(name = "sent_emails", uniqueConstraints = {
        @UniqueConstraint(columnNames = "emailId")
})
public class SentEmail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "emailId")
    private int emailId;

    @Column(name = "timestamp")
    private long timestam;

    @Column(name = "emailText")
    private String emailText;

    @Column(name = "sentTo")
    private int userId;

    public int getEmailId() {
        return emailId;
    }

    public void setEmailId(int emailId) {
        this.emailId = emailId;
    }

    public long getTimestam() {
        return timestam;
    }

    public void setTimestam(long timestam) {
        this.timestam = timestam;
    }

    public String getEmailText() {
        return emailText;
    }

    public void setEmailText(String emailText) {
        this.emailText = emailText;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
