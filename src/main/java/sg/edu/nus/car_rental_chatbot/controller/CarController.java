package sg.edu.nus.car_rental_chatbot.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import sg.edu.nus.car_rental_chatbot.model.Car;
import sg.edu.nus.car_rental_chatbot.repository.CarRepository;

@RestController
public class CarController {

    private final CarRepository carRepository;

    public CarController(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @GetMapping("/api/cars")
    public List<Car> getCars() {
        return carRepository.findAll();
    }
}