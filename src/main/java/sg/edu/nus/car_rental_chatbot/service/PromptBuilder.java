package sg.edu.nus.car_rental_chatbot.service;

import org.springframework.stereotype.Service;

@Service
public class PromptBuilder {

    public String buildPrompt(String userMessage) {
        return """
                You are a car rental assistant.

                Rules:
                1. Only answer questions related to car rental.
                2. Ask follow-up questions if important information is missing.
                3. Do not invent cars, prices, or policies.
                4. Keep the answer clear and helpful.

                User message:
                %s
                """.formatted(userMessage);
    }
}