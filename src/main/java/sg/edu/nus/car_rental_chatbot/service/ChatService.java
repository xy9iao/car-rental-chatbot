package sg.edu.nus.car_rental_chatbot.service;

import java.util.List;

import org.springframework.stereotype.Service;

import sg.edu.nus.car_rental_chatbot.model.Car;

@Service
public class ChatService {

    private final PromptBuilder promptBuilder;
    private final CarSearchService carSearchService;

    public ChatService(PromptBuilder promptBuilder, CarSearchService carSearchService) {
        this.promptBuilder = promptBuilder;
        this.carSearchService = carSearchService;
    }

    public String getReply(String userMessage) {
        List<Car> relevantCars = carSearchService.searchRelevantCars(userMessage);

        String prompt = promptBuilder.buildPrompt(userMessage, relevantCars);

        return "Filtered grounded prompt created successfully:\n\n" + prompt;
    }
}