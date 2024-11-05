package com.example.car_app.service;

import com.example.car_app.model.Car;
import com.example.car_app.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class CarManager {

    @Autowired
    private CarRepository carRepository;

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    public Optional<Car> getCarById(String id) {
        return carRepository.findById(id);
    }

    public Car createCar(Car car) {
        car.setCreatedAt(LocalDateTime.now().format(formatter));
        car.setCreatedMessage("Created at " + LocalDateTime.now().format(formatter) +
                " with make of " + car.getMake() + " and model of " + car.getModel());
        return carRepository.save(car);
    }

    public Car updateCar(String id, Car carDetails) {
        Car car = carRepository.findById(id).orElseThrow(() -> new RuntimeException("Car not found"));
        car.setMake(carDetails.getMake());
        car.setModel(carDetails.getModel());
        car.setColour(carDetails.getColour());
        car.setUpdatedAt(LocalDateTime.now().format(formatter));
        car.setUpdatedMessage("Updated at " + LocalDateTime.now().format(formatter) +
                " with make of " + car.getMake() + " and model of " + car.getModel());
        return carRepository.save(car);
    }

    public void deleteCar(String id) {
        carRepository.deleteById(id);
    }
}

