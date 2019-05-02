package ru.spring.localtaxi.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.spring.localtaxi.domain.PlaceInQueueVF;
import ru.spring.localtaxi.domain.User;
import ru.spring.localtaxi.repository.PlaceInQueueVFRepository;
import ru.spring.localtaxi.repository.UserRepository;
import ru.spring.localtaxi.service.PlaceInQueueVFService;

import java.util.List;

import static ru.spring.localtaxi.domain.PlaceInQueueVF.of;

@Service
@AllArgsConstructor
public class PlaceInQueueVFServiceImpl implements PlaceInQueueVFService {

    private final PlaceInQueueVFRepository repository;

    private final UserRepository userRepository;

    @Override
    public List<PlaceInQueueVF> findAll() {
        return repository.findAll();
    }

    @Override
    public PlaceInQueueVF findByDriverId(Long driverId) {
        return repository.findByDriverId(driverId).orElse(null);
    }

    @Override
    public PlaceInQueueVF findByPassengerId(Long passengerId) {
        return repository.findByPassengersContains(userRepository.findById(passengerId).orElseThrow(
                () -> new RuntimeException("Пользователь не найден!")
        )).orElse(null);
    }

    @Override
    public List<PlaceInQueueVF> addDriverInQueue(Long driverId) {
        User driver = userRepository.findById(driverId).orElseThrow(
                () -> new RuntimeException("Пользователь не найден!")
        );
        PlaceInQueueVF lastPlaceInQueueVF = repository.findFirstByOrderByNumberDesc();
        int number = lastPlaceInQueueVF == null ? 1 : lastPlaceInQueueVF.getNumber() + 1;
        repository.save(of(number, driver, 0));
        return repository.findAll();
    }

    @Override
    public List<PlaceInQueueVF> removeDriverFromQueue(Long driverId) {
        PlaceInQueueVF placeInQueueVF = repository.findByDriverId(driverId).orElseThrow(
                () -> new RuntimeException("Место в очереди не найдено!")
        );
        List<PlaceInQueueVF> placeInQueueVFList = repository.findAllByNumberIsAfter(placeInQueueVF.getNumber());
        placeInQueueVFList.forEach(p -> p.setNumber(p.getNumber() - 1));
        repository.saveAll(placeInQueueVFList);
        repository.delete(placeInQueueVF);
        return repository.findAll();
    }

    @Transactional
    @Override
    public List<PlaceInQueueVF> addPassengerInQueue(Long placeInQueueId, Long passengerId) {
        PlaceInQueueVF placeInQueueVF = repository.findById(placeInQueueId).orElseThrow(
                () -> new RuntimeException("Место в очереди не найдено!")
        );
        if (placeInQueueVF.getNumberPassengers() == 4) {
            throw new RuntimeException("Мест нет");
        }
        if (passengerId != null) {
            User passenger = userRepository.findById(passengerId).orElseThrow(
                    () -> new RuntimeException("Пользователь не найден!")
            );
            if (placeInQueueVF.getPassengers().contains(passenger)) {
                throw new RuntimeException("Пассажир уже в машине!");
            }
            placeInQueueVF.getPassengers().add(passenger);
        }
        placeInQueueVF.setNumberPassengers(placeInQueueVF.getNumberPassengers() + 1);
        repository.save(placeInQueueVF);
        return repository.findAll();
    }

    @Transactional
    @Override
    public List<PlaceInQueueVF> removePassengerFromQueue(Long placeInQueueId, Long passengerId) {
        PlaceInQueueVF placeInQueueVF = repository.findById(placeInQueueId).orElseThrow(
                () -> new RuntimeException("Место в очереди не найдено!")
        );
        if (placeInQueueVF.getNumberPassengers() == 0) {
            throw new RuntimeException("Свободно");
        }
        if (passengerId != null) {
            User passenger = userRepository.findById(passengerId).orElseThrow(
                    () -> new RuntimeException("Пользователь не найден!")
            );
            placeInQueueVF.getPassengers().remove(passenger);
        }
        placeInQueueVF.setNumberPassengers(placeInQueueVF.getNumberPassengers() - 1);
        repository.save(placeInQueueVF);
        return repository.findAll();
    }
}
