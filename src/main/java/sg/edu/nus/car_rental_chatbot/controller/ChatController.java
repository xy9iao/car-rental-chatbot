package sg.edu.nus.car_rental_chatbot.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import sg.edu.nus.car_rental_chatbot.dto.ChatRequest;
import sg.edu.nus.car_rental_chatbot.dto.ChatResponse;

@RestController
public class ChatController {

    @PostMapping("/api/chat")
    public ChatResponse chat(@RequestBody ChatRequest request) {
        String userMessage = request.getMessage();

        String reply = "You said: " + userMessage
                + ". I am the car rental chatbot. AI connection will be added next.";

        return new ChatResponse(reply);
    }
}