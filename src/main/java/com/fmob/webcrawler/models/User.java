package com.fmob.webcrawler.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = "userId"),
        @UniqueConstraint(columnNames = "email")
})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userId")
    private int userID;

    @Column(name = "email")
    private String email;

    @Column(name = "origin")
    private String origin;

    @Column(name = "destination")
    private String destination;

    @Column(name = "confirmed")
    private boolean confirmed;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "frequencyId", referencedColumnName = "frequencyID")
    private Frequency frequency;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "sentTo", referencedColumnName = "userId")
    private List<SentEmail> sentEmails;

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Frequency getFrequency() {
        return frequency;
    }

    public void setFrequency(Frequency frequency) {
        this.frequency = frequency;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    public List<SentEmail> getSentEmails() {
        return sentEmails;
    }

    public void setSentEmails(List<SentEmail> sentEmails) {
        this.sentEmails = sentEmails;
    }
}
