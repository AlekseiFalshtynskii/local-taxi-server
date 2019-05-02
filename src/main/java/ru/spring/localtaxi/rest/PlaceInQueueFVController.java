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

    @GetMapping("/api/queue/f/v/driver/{driverId}")
    public PlaceInQueueFV findByDriverId(@PathVariable Long driverId) {
        return service.findByDriverId(driverId);
    }

    @GetMapping("/api/queue/f/v/passenger/{passengerId}")
    public PlaceInQueueFV findByPassengerId(@PathVariable Long passengerId) {
        return service.findByPassengerId(passengerId);
    }

    @PostMapping("/api/queue/f/v/driver/{driverId}")
    public List<PlaceInQueueFV> addDriverInQueue(@PathVariable Long driverId) {
        return service.addDriverInQueue(driverId);
    }

    @DeleteMapping("/api/queue/f/v/driver/{driverId}")
    public List<PlaceInQueueFV> removeDriverFromQueue(@PathVariable Long driverId) {
        return service.removeDriverFromQueue(driverId);
    }

    @PostMapping({
            "/api/queue/f/v/{placeInQueueId}/passenger",
            "/api/queue/f/v/{placeInQueueId}/passenger/{passengerId}"
    })
    public List<PlaceInQueueFV> addPassengerInQueue(@PathVariable Long placeInQueueId, @PathVariable(required = false) Long passengerId) {
        return service.addPassengerInQueue(placeInQueueId, passengerId);
    }

    @DeleteMapping({
            "/api/queue/f/v/{placeInQueueId}/passenger",
            "/api/queue/f/v/{placeInQueueId}/passenger/{passengerId}"
    })
    public List<PlaceInQueueFV> removePassengerFromQueue(@PathVariable Long placeInQueueId, @PathVariable(required = false) Long passengerId) {
        return service.removePassengerFromQueue(placeInQueueId, passengerId);
    }
}
