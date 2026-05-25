package sg.edu.nus.car_rental_chatbot.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import sg.edu.nus.car_rental_chatbot.model.ChatMessage;

@Service
public class ConversationHistoryService {

    private final List<ChatMessage> history = new ArrayList<>();

    private static final int MAX_MESSAGES = 8;

    public void addUserMessage(String message) {
        history.add(new ChatMessage("user", message));
        trimHistory();
    }

    public void addAssistantMessage(String message) {
        history.add(new ChatMessage("assistant", message));
        trimHistory();
    }

    public List<ChatMessage> getRecentHistory() {
        return new ArrayList<>(history);
    }

    public void clearHistory() {
        history.clear();
    }

    private void trimHistory() {
        while (history.size() > MAX_MESSAGES) {
            history.remove(0);
        }
    }
}