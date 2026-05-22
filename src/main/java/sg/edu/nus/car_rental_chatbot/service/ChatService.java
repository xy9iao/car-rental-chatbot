package sg.edu.nus.car_rental_chatbot.service;

import org.springframework.stereotype.Service;

@Service
public class ChatService {

    private final PromptBuilder promptBuilder;

    public ChatService(PromptBuilder promptBuilder) {
        this.promptBuilder = promptBuilder;
    }

    public String getReply(String userMessage) {
        String prompt = promptBuilder.buildPrompt(userMessage);

        return "Prompt created successfully:\n\n" + prompt;
    }
}
