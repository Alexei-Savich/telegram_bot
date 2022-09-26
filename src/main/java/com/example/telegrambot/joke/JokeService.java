package com.example.telegrambot.joke;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JokeService {

    private final JokeRepository jokeRepository;

    @Autowired
    public JokeService(JokeRepository jokeRepository) {
        this.jokeRepository = jokeRepository;
    }

    public List<Joke> findAllJokes() {
        return jokeRepository.findAll();
    }

    public void addJoke(Joke joke) {
        jokeRepository.save(joke);
    }

    public Optional<Joke> getJokeById(Long id) {
        return jokeRepository.findById(id);
    }

    public void deleteById(Long id) {
        jokeRepository.deleteById(id);
    }

    public Optional<Joke> getRandomJoke(){
        return jokeRepository.getRandomJoke();
    }

    public Optional<Joke> getShortJoke(){
        return jokeRepository.getShortJoke();
    }

    public Optional<Joke> getMediumJoke(){
        return jokeRepository.getMediumJoke();
    }

    public Optional<Joke> getLongJoke(){
        return jokeRepository.getLongJoke();
    }

}
