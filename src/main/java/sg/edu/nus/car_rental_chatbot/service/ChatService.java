package sg.edu.nus.car_rental_chatbot.service;

import java.util.List;

import org.springframework.stereotype.Service;

import sg.edu.nus.car_rental_chatbot.model.Car;
import sg.edu.nus.car_rental_chatbot.repository.CarRepository;

@Service
public class ChatService {

    private final PromptBuilder promptBuilder;
    private final CarRepository carRepository;

    public ChatService(PromptBuilder promptBuilder, CarRepository carRepository) {
        this.promptBuilder = promptBuilder;
        this.carRepository = carRepository;
    }

    public String getReply(String userMessage) {
        List<Car> cars = carRepository.findAll();

        String prompt = promptBuilder.buildPrompt(userMessage, cars);

        return "Grounded prompt created successfully:\n\n" + prompt;
    }
}