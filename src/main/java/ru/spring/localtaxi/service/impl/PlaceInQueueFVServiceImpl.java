package ru.spring.localtaxi.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.spring.localtaxi.domain.PlaceInQueueFV;
import ru.spring.localtaxi.domain.User;
import ru.spring.localtaxi.repository.PlaceInQueueFVRepository;
import ru.spring.localtaxi.repository.UserRepository;
import ru.spring.localtaxi.service.PlaceInQueueFVService;

import java.util.List;

import static ru.spring.localtaxi.domain.PlaceInQueueFV.of;

@Service
@AllArgsConstructor
public class PlaceInQueueFVServiceImpl implements PlaceInQueueFVService {

    private final PlaceInQueueFVRepository repository;

    private final UserRepository userRepository;

    @Override
    public List<PlaceInQueueFV> findAll() {
        return repository.findAll();
    }

    @Override
    public PlaceInQueueFV findByDriverId(Long driverId) {
        return repository.findByDriverId(driverId).orElse(null);
    }

    @Override
    public PlaceInQueueFV findByPassengerId(Long passengerId) {
        return repository.findByPassengersContains(userRepository.findById(passengerId).orElseThrow(
                () -> new RuntimeException("Пользователь не найден!")
        )).orElse(null);
    }

    @Transactional
    @Override
    public List<PlaceInQueueFV> addDriverInQueue(Long driverId) {
        User driver = userRepository.findById(driverId).orElseThrow(
                () -> new RuntimeException("Пользователь не найден!")
        );
        PlaceInQueueFV lastPlaceInQueueFV = repository.findFirstByOrderByNumberDesc();
        int number = lastPlaceInQueueFV == null ? 1 : lastPlaceInQueueFV.getNumber() + 1;
        repository.save(of(number, driver, 0));
        return repository.findAll();
    }

    @Transactional
    @Override
    public List<PlaceInQueueFV> removeDriverFromQueue(Long driverId) {
        PlaceInQueueFV placeInQueueFV = repository.findByDriverId(driverId).orElseThrow(
                () -> new RuntimeException("Место в очереди не найдено!")
        );
        List<PlaceInQueueFV> placeInQueueFVList = repository.findAllByNumberIsAfter(placeInQueueFV.getNumber());
        placeInQueueFVList.forEach(p -> p.setNumber(p.getNumber() - 1));
        repository.saveAll(placeInQueueFVList);
        repository.delete(placeInQueueFV);
        repository.flush();
        return repository.findAll();
    }

    @Transactional
    @Override
    public List<PlaceInQueueFV> addPassengerInQueue(Long placeInQueueId, Long passengerId) {
        PlaceInQueueFV placeInQueueFV = repository.findById(placeInQueueId).orElseThrow(
                () -> new RuntimeException("Место в очереди не найдено!")
        );
        if (placeInQueueFV.getNumberPassengers() == 4) {
            throw new RuntimeException("Мест нет");
        }
        if (passengerId != null) {
            User passenger = userRepository.findById(passengerId).orElseThrow(
                    () -> new RuntimeException("Пользователь не найден!")
            );
            if (placeInQueueFV.getPassengers().contains(passenger)) {
                throw new RuntimeException("Пассажир уже в машине!");
            }
            placeInQueueFV.getPassengers().add(passenger);
        }
        placeInQueueFV.setNumberPassengers(placeInQueueFV.getNumberPassengers() + 1);
        repository.save(placeInQueueFV);
        return repository.findAll();
    }

    @Transactional
    @Override
    public List<PlaceInQueueFV> removePassengerFromQueue(Long placeInQueueId, Long passengerId) {
        PlaceInQueueFV placeInQueueFV = repository.findById(placeInQueueId).orElseThrow(
                () -> new RuntimeException("Место в очереди не найдено!")
        );
        if (placeInQueueFV.getNumberPassengers() == 0) {
            throw new RuntimeException("Свободно");
        }
        if (passengerId != null) {
            User passenger = userRepository.findById(passengerId).orElseThrow(
                    () -> new RuntimeException("Пользователь не найден!")
            );
            placeInQueueFV.getPassengers().remove(passenger);
        }
        placeInQueueFV.setNumberPassengers(placeInQueueFV.getNumberPassengers() - 1);
        repository.save(placeInQueueFV);
        return repository.findAll();
    }
}
