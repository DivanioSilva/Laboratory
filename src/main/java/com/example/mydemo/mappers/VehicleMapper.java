package com.example.mydemo.mappers;

import com.example.mydemo.domain.Vehicle;
import com.example.mydemo.domain.VehicleDto;
import com.example.mydemo.domain.VehicleDtoWithId;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface VehicleMapper {
    Vehicle vehicleDtoToVehicle(VehicleDto vehicleDto);

    VehicleDto vehicleToVehicleDto(Vehicle vehicle);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateVehicleFromVehicleDto(VehicleDto vehicleDto, @MappingTarget Vehicle vehicle);

    Vehicle vehicleDtoWithIdToVehicle(VehicleDtoWithId vehicleDtoWithId);

    VehicleDtoWithId vehicleToVehicleDtoWithId(Vehicle vehicle);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateVehicleFromVehicleDtoWithId(VehicleDtoWithId vehicleDtoWithId, @MappingTarget Vehicle vehicle);

    List<VehicleDtoWithId> vehiclesToVehiclesDtoWithId(List<Vehicle> vehicleList);
}
