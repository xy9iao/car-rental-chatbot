package sg.edu.nus.car_rental_chatbot.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import sg.edu.nus.car_rental_chatbot.model.Car;

@Repository
public class CarRepository {

    private final List<Car> cars = List.of(
            new Car(1, "Toyota Corolla", "Sedan", 5, 55.0, true),
            new Car(2, "Honda CR-V", "SUV", 5, 78.0, true),
            new Car(3, "Toyota Sienna", "MPV", 7, 110.0, true),
            new Car(4, "BMW 3 Series", "Luxury", 5, 150.0, false),
            new Car(5, "Nissan X-Trail", "SUV", 5, 82.0, true)
    );

    public List<Car> findAll() {
        return cars;
    }
}