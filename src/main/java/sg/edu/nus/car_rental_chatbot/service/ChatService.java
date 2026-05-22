package sg.edu.nus.car_rental_chatbot.service;

import org.springframework.stereotype.Service;

@Service
public class ChatService {

    public String getReply(String userMessage) {
        return "You said: " + userMessage
                + ". I am the car rental chatbot. Service layer is working.";
    }
}