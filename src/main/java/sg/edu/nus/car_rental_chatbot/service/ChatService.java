package sg.edu.nus.car_rental_chatbot.service;

import org.springframework.stereotype.Service;

import sg.edu.nus.car_rental_chatbot.client.AIClient;

@Service
public class ChatService {

    private final PromptBuilder promptBuilder;
    private final DatasetContextService datasetContextService;
    private final AIClient aiClient;

    public ChatService(
            PromptBuilder promptBuilder,
            DatasetContextService datasetContextService,
            AIClient aiClient
    ) {
        this.promptBuilder = promptBuilder;
        this.datasetContextService = datasetContextService;
        this.aiClient = aiClient;
    }

    public String getReply(String userMessage) {
        String datasetContext = datasetContextService.buildDatasetContext();
        String prompt = promptBuilder.buildPrompt(userMessage, datasetContext);
        return aiClient.callAI(prompt);
    }
}