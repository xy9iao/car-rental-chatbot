package sg.edu.nus.car_rental_chatbot.service;

import java.util.List;

import org.springframework.stereotype.Service;

import sg.edu.nus.car_rental_chatbot.model.ChatMessage;

@Service
public class PromptBuilder {

    public String buildPrompt(
            String userMessage,
            String datasetContext,
            List<ChatMessage> history
    ) {
        return """
                You are a car rental assistant.

                Your task:
                Answer the user's car rental question using the dataset context below and the recent conversation history.

                Strict rules:
                1. Only answer car rental related questions.
                2. Use the dataset context as your source of information.
                3. Do not invent cars, prices, policies, discounts, or availability.
                4. If the dataset does not contain enough information, say what is missing and ask a follow-up question.
                5. If the user asks something unrelated to car rental, politely say you can only help with car rental questions.
                6. Use the conversation history to understand follow-up questions.
                7. Keep the answer short, clear, and practical.

                Dataset context:
                %s

                Recent conversation history:
                %s

                Current user message:
                %s
                """.formatted(
                datasetContext,
                formatHistory(history),
                userMessage
        );
    }

    private String formatHistory(List<ChatMessage> history) {
        if (history == null || history.isEmpty()) {
            return "No previous conversation.";
        }

        StringBuilder builder = new StringBuilder();

        for (ChatMessage message : history) {
            builder.append(message.getRole())
                    .append(": ")
                    .append(message.getContent())
                    .append("\n");
        }

        return builder.toString();
    }
}