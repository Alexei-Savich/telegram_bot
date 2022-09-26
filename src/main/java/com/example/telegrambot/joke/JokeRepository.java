package com.example.telegrambot.joke;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JokeRepository extends JpaRepository<Joke, Long> {

    int LONG_JOKE_SIZE = 800;
    int MEDIUM_JOKE_SIZE = 200;
    String SHORT_JOKE_QUERY = "SELECT * FROM joke WHERE size < " + MEDIUM_JOKE_SIZE + " ORDER BY RAND() LIMIT 1";
    String MEDIUM_JOKE_QUERY = "SELECT * FROM joke WHERE size >= " + MEDIUM_JOKE_SIZE + " AND size < " + LONG_JOKE_SIZE + " ORDER BY RAND() LIMIT 1";
    String LONG_JOKE_QUERY = "SELECT * FROM joke WHERE size >= " + LONG_JOKE_SIZE + " ORDER BY RAND() LIMIT 1";
    String RANDOM_JOKE_QUERY = "SELECT * FROM joke ORDER BY RAND() LIMIT 1";

    @Query(value = SHORT_JOKE_QUERY, nativeQuery = true)
    Optional<Joke> getShortJoke();

    @Query(value = MEDIUM_JOKE_QUERY, nativeQuery = true)
    Optional<Joke> getMediumJoke();

    @Query(value = LONG_JOKE_QUERY, nativeQuery = true)
    Optional<Joke> getLongJoke();

    @Query(value = RANDOM_JOKE_QUERY, nativeQuery = true)
    Optional<Joke> getRandomJoke();

}
