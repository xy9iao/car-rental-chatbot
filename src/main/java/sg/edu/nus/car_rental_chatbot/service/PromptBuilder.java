package sg.edu.nus.car_rental_chatbot.service;

import org.springframework.stereotype.Service;

@Service
public class PromptBuilder {

    public String buildPrompt(String userMessage, String datasetContext) {
        return """
                You are a car rental assistant.

                Rules:
                1. Only answer questions related to car rental.
                2. Use only the dataset context below.
                3. Do not invent cars, prices, policies, or availability.
                4. If the dataset does not contain the answer, say so clearly.
                5. Keep the answer clear and helpful.

                Dataset context:
                %s

                User message:
                %s
                """.formatted(datasetContext, userMessage);
    }
}