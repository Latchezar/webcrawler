package com.fmob.webcrawler.models;

import javax.persistence.*;

@Entity
@Table(name = "frequencies", uniqueConstraints = {
        @UniqueConstraint(columnNames = "frequencyId")
})
public class Frequency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "frequencyId")
    private int frequencyId;

    @Column(name = "frequencyPeriod")
    private String frequencyPeriod;

    public int getFrequencyId() {
        return frequencyId;
    }

    public void setFrequencyId(int frequencyId) {
        this.frequencyId = frequencyId;
    }

    public String getFrequencyPeriod() {
        return frequencyPeriod;
    }

    public void setFrequencyPeriod(String frequencyPeriod) {
        this.frequencyPeriod = frequencyPeriod;
    }
}
