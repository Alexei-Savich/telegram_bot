package com.example.telegrambot.bot;

import com.example.telegrambot.joke.Joke;
import com.example.telegrambot.joke.JokeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class TelegramBot extends TelegramLongPollingBot {

    private static final String HELP_MESSAGE = "/random - get random joke\n" +
            "/random_short - get random short joke\n" +
            "/random_medium - get random medium joke\n" +
            "/random_long - get random long joke\n" +
            "/help - show that message again";
    private final String botToken;
    private final String botUsername;

    @Autowired
    private final JokeService jokeService;

    public TelegramBot(@Value("${bot.token}") String botToken, @Value("${bot.username}") String botUsername, JokeService messageService) {
        this.botToken = botToken;
        this.botUsername = botUsername;
        this.jokeService = messageService;
    }

    @Override
    public String getBotUsername() {
        return botUsername;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText() && update.getMessage().getText().equals("/help")) {
            sendMessage(update.getMessage().getFrom().getId(), HELP_MESSAGE);
        }
        if (update.hasMessage() && update.getMessage().hasText() && update.getMessage().getText().equals("/random")) {
            sendMessage(update.getMessage().getFrom().getId(), jokeService.getRandomJoke().orElse(new Joke("No joke")).getText());
        }
        if (update.hasMessage() && update.getMessage().hasText() && update.getMessage().getText().equals("/random_short")) {
            sendMessage(update.getMessage().getFrom().getId(), jokeService.getShortJoke().orElse(new Joke("No joke")).getText());
        }
        if (update.hasMessage() && update.getMessage().hasText() && update.getMessage().getText().equals("/random_medium")) {
            sendMessage(update.getMessage().getFrom().getId(), jokeService.getMediumJoke().orElse(new Joke("No joke")).getText());
        }
        if (update.hasMessage() && update.getMessage().hasText() && update.getMessage().getText().equals("/random_long")) {
            sendMessage(update.getMessage().getFrom().getId(), jokeService.getLongJoke().orElse(new Joke("No joke")).getText());
        }
    }

    private void sendMessage(Long chatId, String text) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(text);
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
