package com.example.car_app.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.example.car_app.model.Car;
import java.util.Optional;

public interface CarRepository extends MongoRepository<Car, String> {
    @SuppressWarnings("null")
    Optional<Car> findById(Long id);

    void deleteById(String id);

    Optional<Car> findById(String id);
}
