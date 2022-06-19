package com.example.mydemo.service;

import com.example.mydemo.domain.Vehicle;
import com.example.mydemo.domain.VehicleDto;
import com.example.mydemo.domain.VehicleDtoWithId;
import com.example.mydemo.exceptions.VehicleNotFoundException;
import com.example.mydemo.mappers.VehicleMapper;
import com.example.mydemo.repository.VehicleRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class VehicleServiceImpl implements VehicleService {
    private final VehicleRepository vehicleRepository;
    private final VehicleMapper vehicleMapper;

    @Override
    public VehicleDtoWithId saveVehicle(VehicleDto vehicleDto){
        Vehicle vehicle = this.vehicleMapper.vehicleDtoToVehicle(vehicleDto);
        Vehicle save = this.vehicleRepository.save(vehicle);
        return this.vehicleMapper.vehicleToVehicleDtoWithId(save);
    }

    @Override
    @SneakyThrows
    public VehicleDtoWithId updateVehicle(VehicleDtoWithId vehicleDto){
        Vehicle vehicle = this.vehicleRepository
                .findById(vehicleDto.getId())
                .orElseThrow(VehicleNotFoundException::new);

        this.vehicleMapper.updateVehicleFromVehicleDtoWithId(vehicleDto, vehicle);
        Vehicle save = this.vehicleRepository.save(vehicle);
        return this.vehicleMapper.vehicleToVehicleDtoWithId(save);
    }

    @Override
    public List<VehicleDtoWithId> findAll(){
        List<Vehicle> vehicleList = this.vehicleRepository.findAll();
        return this.vehicleMapper.vehiclesToVehiclesDtoWithId(vehicleList);
    }

    @Override
    @SneakyThrows
    public VehicleDtoWithId findById(Long vehicleId){
        Vehicle vehicle = this.vehicleRepository
                .findById(vehicleId)
                .orElseThrow(VehicleNotFoundException::new);
        return this.vehicleMapper.vehicleToVehicleDtoWithId(vehicle);
    }

    @Override
    @SneakyThrows
    public VehicleDtoWithId findByPlate(String vehiclePlate){
        Vehicle vehicle = this.vehicleRepository
                .findByPlate(vehiclePlate)
                .orElseThrow(VehicleNotFoundException::new);
        return this.vehicleMapper.vehicleToVehicleDtoWithId(vehicle);
    }
}