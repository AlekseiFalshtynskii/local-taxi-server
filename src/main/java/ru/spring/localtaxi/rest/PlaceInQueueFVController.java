package ru.spring.localtaxi.rest;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.spring.localtaxi.domain.PlaceInQueueFV;
import ru.spring.localtaxi.service.PlaceInQueueFVService;

import java.util.List;

@RestController
@AllArgsConstructor
public class PlaceInQueueFVController {

    private final PlaceInQueueFVService service;

    @GetMapping("/api/queue/f/v")
    public List<PlaceInQueueFV> findAll() {
        return service.findAll();
    }

    @GetMapping("/api/queue/f/v/driver")
    public PlaceInQueueFV findByDriverId(@RequestParam(name = "driverId") Long driverId) {
        return service.findByDriverId(driverId);
    }

    @GetMapping("/api/queue/f/v/passenger")
    public PlaceInQueueFV findByPassengerId(@RequestParam(name = "passengerId") Long passengerId) {
        return service.findByPassengerId(passengerId);
    }

    @PostMapping("/api/queue/f/v/driver")
    public List<PlaceInQueueFV> addDriverInQueue(@RequestParam(name = "driverId") Long driverId) {
        return service.addDriverInQueue(driverId);
    }

    @DeleteMapping("/api/queue/f/v/driver")
    public List<PlaceInQueueFV> removeDriverFromQueue(@RequestParam(name = "driverId") Long driverId) {
        return service.removeDriverFromQueue(driverId);
    }

    @PostMapping({"/api/queue/f/v/{placeInQueueId}/passenger"})
    public List<PlaceInQueueFV> addPassengerInQueue(
            @PathVariable Long placeInQueueId,
            @RequestParam(name = "passengerId", required = false) Long passengerId
    ) {
        return service.addPassengerInQueue(placeInQueueId, passengerId);
    }

    @DeleteMapping({"/api/queue/f/v/{placeInQueueId}/passenger"})
    public List<PlaceInQueueFV> removePassengerFromQueue(
            @PathVariable Long placeInQueueId,
            @RequestParam(name = "passengerId", required = false) Long passengerId
    ) {
        return service.removePassengerFromQueue(placeInQueueId, passengerId);
    }
}
