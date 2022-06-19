package com.example.mydemo.controller;

import com.example.mydemo.domain.VehicleDto;
import com.example.mydemo.domain.VehicleDtoWithId;
import com.example.mydemo.service.VehicleService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RequestMapping("/vehicle")
@RestController
public class VehicleController {
    private final VehicleService vehicleService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public VehicleDtoWithId savePerson(@RequestBody VehicleDto vehicleDto) {
        return vehicleService.saveVehicle(vehicleDto);
    }


    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public VehicleDtoWithId updatePerson(@RequestBody VehicleDtoWithId vehicleDtoWithId) {
        return vehicleService.updateVehicle(vehicleDtoWithId);
    }

    @GetMapping(value = "/id/{vehicleId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public VehicleDtoWithId findById(@PathVariable("vehicleId") Long vehicleId) {
        return this.vehicleService.findById(vehicleId);
    }

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<VehicleDtoWithId> getAllVehicle() {
        return this.vehicleService.findAll();
    }

    @GetMapping(value = "/plate/{vehiclePlate}", produces = MediaType.APPLICATION_JSON_VALUE)
    public VehicleDtoWithId findByPlate(@PathVariable("vehiclePlate") String vehiclePlate) {
        return this.vehicleService.findByPlate(vehiclePlate);
    }
}