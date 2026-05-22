package sg.edu.nus.car_rental_chatbot.service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import sg.edu.nus.car_rental_chatbot.model.Car;
import sg.edu.nus.car_rental_chatbot.repository.CarRepository;

@Service
public class CarSearchService {

    private final CarRepository carRepository;

    public CarSearchService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public List<Car> searchRelevantCars(String userMessage) {
        String message = userMessage.toLowerCase();

        List<Car> cars = carRepository.findAll();

        if (message.contains("suv")) {
            cars = cars.stream()
                    .filter(car -> car.getType().equalsIgnoreCase("SUV"))
                    .collect(Collectors.toList());
        } else if (message.contains("sedan")) {
            cars = cars.stream()
                    .filter(car -> car.getType().equalsIgnoreCase("Sedan"))
                    .collect(Collectors.toList());
        } else if (message.contains("mpv") || message.contains("7-seater") || message.contains("7 seater")) {
            cars = cars.stream()
                    .filter(car -> car.getType().equalsIgnoreCase("MPV") || car.getSeats() >= 7)
                    .collect(Collectors.toList());
        } else if (message.contains("luxury")) {
            cars = cars.stream()
                    .filter(car -> car.getType().equalsIgnoreCase("Luxury"))
                    .collect(Collectors.toList());
        }

        if (message.contains("cheap") || message.contains("cheapest") || message.contains("budget")) {
            cars = cars.stream()
                    .sorted(Comparator.comparingDouble(Car::getPricePerDay))
                    .collect(Collectors.toList());
        }

        return cars;
    }
}