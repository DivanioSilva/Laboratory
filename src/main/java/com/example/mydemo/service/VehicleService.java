package com.example.mydemo.service;

import com.example.mydemo.domain.VehicleDto;
import com.example.mydemo.domain.VehicleDtoWithId;
import lombok.SneakyThrows;

import java.util.List;

public interface VehicleService {
    VehicleDtoWithId saveVehicle(VehicleDto vehicleDto);

    @SneakyThrows
    VehicleDtoWithId updateVehicle(VehicleDtoWithId vehicleDto);

    List<VehicleDtoWithId> findAll();

    @SneakyThrows
    VehicleDtoWithId findById(Long vehicleId);
    @SneakyThrows
    VehicleDtoWithId findByPlate(String vehiclePlate);
}