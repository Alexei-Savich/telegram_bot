package com.example.telegrambot.joke;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Joke {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(columnDefinition = "MEDIUMBLOB")
    private String text;
    private int size;

    public Joke(String text) {
        this.text = text;
        this.size = text.length();
    }

    @Override
    public String toString() {
        return text;
    }

}
