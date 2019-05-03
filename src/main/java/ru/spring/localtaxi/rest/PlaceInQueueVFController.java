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

    @GetMapping("/api/queue/v/f/driver")
    public PlaceInQueueVF findByDriverId(@RequestParam(name = "driverId") Long driverId) {
        return service.findByDriverId(driverId);
    }

    @GetMapping("/api/queue/v/f/passenger")
    public PlaceInQueueVF findByPassengerId(@RequestParam(name = "passengerId") Long passengerId) {
        return service.findByPassengerId(passengerId);
    }

    @PostMapping("/api/queue/v/f/driver")
    public List<PlaceInQueueVF> addDriverInQueue(@RequestParam(name = "driverId") Long driverId) {
        return service.addDriverInQueue(driverId);
    }

    @DeleteMapping("/api/queue/v/f/driver")
    public List<PlaceInQueueVF> removeDriverFromQueue(@RequestParam(name = "driverId") Long driverId) {
        return service.removeDriverFromQueue(driverId);
    }

    @PostMapping({"/api/queue/v/f/{placeInQueueId}/passenger"})
    public List<PlaceInQueueVF> addPassengerInQueue(
            @PathVariable Long placeInQueueId,
            @RequestParam(name = "passengerId", required = false) Long passengerId
    ) {
        return service.addPassengerInQueue(placeInQueueId, passengerId);
    }

    @DeleteMapping({"/api/queue/v/f/{placeInQueueId}/passenger"})
    public List<PlaceInQueueVF> removePassengerFromQueue(
            @PathVariable Long placeInQueueId,
            @RequestParam(name = "passengerId", required = false) Long passengerId
    ) {
        return service.removePassengerFromQueue(placeInQueueId, passengerId);
    }
}
