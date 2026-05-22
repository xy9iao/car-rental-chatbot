package sg.edu.nus.car_rental_chatbot.service;

import java.util.List;

import org.springframework.stereotype.Service;

import sg.edu.nus.car_rental_chatbot.model.Car;

@Service
public class PromptBuilder {

    public String buildPrompt(String userMessage, List<Car> cars) {
        String carData = formatCars(cars);

        return """
                You are a car rental assistant.

                Rules:
                1. Only answer questions related to car rental.
                2. Use only the available car data below.
                3. Do not invent cars, prices, or availability.
                4. If the user asks for unavailable information, ask a follow-up question.
                5. Keep the answer clear and helpful.

                Available car data:
                %s

                User message:
                %s
                """.formatted(carData, userMessage);
    }

    private String formatCars(List<Car> cars) {
        StringBuilder builder = new StringBuilder();

        for (Car car : cars) {
            builder.append("- ")
                    .append(car.getName())
                    .append(" | Type: ").append(car.getType())
                    .append(" | Seats: ").append(car.getSeats())
                    .append(" | Price per day: $").append(car.getPricePerDay())
                    .append(" | Available: ").append(car.isAvailable())
                    .append("\n");
        }

        return builder.toString();
    }
}