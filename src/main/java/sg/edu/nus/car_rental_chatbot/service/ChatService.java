package sg.edu.nus.car_rental_chatbot.service;

import java.util.List;

import org.springframework.stereotype.Service;

import sg.edu.nus.car_rental_chatbot.client.AIClient;
import sg.edu.nus.car_rental_chatbot.model.ChatMessage;

@Service
public class ChatService {

    private final DatasetContextService datasetContextService;
    private final PromptBuilder promptBuilder;
    private final AIClient aiClient;
    private final ConversationHistoryService conversationHistoryService;

    public ChatService(
            DatasetContextService datasetContextService,
            PromptBuilder promptBuilder,
            AIClient aiClient,
            ConversationHistoryService conversationHistoryService
    ) {
        this.datasetContextService = datasetContextService;
        this.promptBuilder = promptBuilder;
        this.aiClient = aiClient;
        this.conversationHistoryService = conversationHistoryService;
    }

    public String getReply(String userMessage) {
        if (userMessage == null || userMessage.trim().isEmpty()) {
            return "Please enter a message.";
        }

        if (userMessage.length() > 500) {
            return "Your message is too long. Please keep it under 500 characters.";
        }

        String datasetContext = datasetContextService.buildDatasetContext();

        List<ChatMessage> history = conversationHistoryService.getRecentHistory();

        String prompt = promptBuilder.buildPrompt(
                userMessage,
                datasetContext,
                history
        );

        String assistantReply = aiClient.callAI(prompt);

        conversationHistoryService.addUserMessage(userMessage);
        conversationHistoryService.addAssistantMessage(assistantReply);

        return assistantReply;
    }

    public void clearHistory() {
        conversationHistoryService.clearHistory();
    }
}