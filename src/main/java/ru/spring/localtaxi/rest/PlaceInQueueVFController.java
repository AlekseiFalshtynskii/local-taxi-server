package ru.spring.localtaxi.rest;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.spring.localtaxi.domain.PlaceInQueueVF;
import ru.spring.localtaxi.service.PlaceInQueueVFService;

import java.util.List;

@RestController
@AllArgsConstructor
public class PlaceInQueueVFController {

    private final PlaceInQueueVFService service;

    @GetMapping("/api/queue/v/f")
    public List<PlaceInQueueVF> findAll() {
        return service.findAll();
    }

    @GetMapping("/api/queue/v/f/driver/{driverId}")
    public PlaceInQueueVF findByDriverId(@PathVariable Long driverId) {
        return service.findByDriverId(driverId);
    }

    @GetMapping("/api/queue/v/f/passenger/{passengerId}")
    public PlaceInQueueVF findByPassengerId(@PathVariable Long passengerId) {
        return service.findByPassengerId(passengerId);
    }

    @PostMapping("/api/queue/v/f/driver/{driverId}")
    public List<PlaceInQueueVF> addDriverInQueue(@PathVariable Long driverId) {
        return service.addDriverInQueue(driverId);
    }

    @DeleteMapping("/api/queue/v/f/driver/{driverId}")
    public List<PlaceInQueueVF> removeDriverFromQueue(@PathVariable Long driverId) {
        return service.removeDriverFromQueue(driverId);
    }

    @PostMapping({
            "/api/queue/v/f/{placeInQueueId}/passenger",
            "/api/queue/v/f/{placeInQueueId}/passenger/{passengerId}"
    })
    public List<PlaceInQueueVF> addPassengerInQueue(@PathVariable Long placeInQueueId, @PathVariable(required = false) Long passengerId) {
        return service.addPassengerInQueue(placeInQueueId, passengerId);
    }

    @DeleteMapping({
            "/api/queue/v/f/{placeInQueueId}/passenger",
            "/api/queue/v/f/{placeInQueueId}/passenger/{passengerId}"
    })
    public List<PlaceInQueueVF> removePassengerFromQueue(@PathVariable Long placeInQueueId, @PathVariable(required = false) Long passengerId) {
        return service.removePassengerFromQueue(placeInQueueId, passengerId);
    }
}
