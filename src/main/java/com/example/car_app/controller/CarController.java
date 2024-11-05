package com.example.car_app.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.car_app.model.Car;
import com.example.car_app.service.CarManager;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cars")
@Slf4j
public class CarController {

    @Autowired
    private CarManager carManager;

    @GetMapping
    public List<Car> getAllCars() {
        return carManager.getAllCars();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable String id) {
        try {
            Optional<Car> carOptional = carManager.getCarById(id);
            if (carOptional.isPresent()) {
                log.info("thisismycar {}", carOptional.get());
                return ResponseEntity.ok(carOptional.get());
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            log.error("Error fetching car by ID", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<Car> createCar(@RequestBody Car car) {
        try {
            Car savedCar = carManager.createCar(car);
            return ResponseEntity.ok(savedCar);
        } catch (Exception e) {
            log.error("Error creating car", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Car> updateCar(@PathVariable String id, @RequestBody Car carDetails) {
        try {
            Car updatedCar = carManager.updateCar(id, carDetails);
            return ResponseEntity.ok(updatedCar);
        } catch (Exception e) {
            log.error("Error updating car", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable String id) {
        try {
            carManager.deleteCar(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            log.error("Error deleting car", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
