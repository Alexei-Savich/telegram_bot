package com.example.telegrambot.joke;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.DataIntegrityViolationException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

@Configuration
public class JokeConfig {

    public String readStringFromFile(String path) throws IOException {
        try (InputStream stream = JokeConfig.class.getClassLoader().getResourceAsStream(path);
             Scanner scanner = new Scanner(stream)) {
            StringBuffer res = new StringBuffer();
            while (scanner.hasNextLine()) {
                res.append(scanner.nextLine());
                if (scanner.hasNextLine()) {
                    res.append("\n");
                }
            }
            return res.toString();
        }
    }

    public String[] separateJokes(String data) {
        return data.split("\n\n");
    }

    @Bean
    public CommandLineRunner commandLineRunner(JokeRepository jokeRepository) {
        String pathToData = "static/joke/jokes.txt";
        return args -> {
            String[] jokes = separateJokes(readStringFromFile(pathToData));
            for (int i = 0; i < jokes.length; i++) {
                try {
                    jokeRepository.save(new Joke((long) (i + 1), jokes[i], jokes[i].length()));
                } catch (DataIntegrityViolationException ignored) {}
            }
        };
    }

}
