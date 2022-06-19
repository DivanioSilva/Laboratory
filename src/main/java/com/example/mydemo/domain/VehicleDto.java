package com.example.mydemo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleDto implements Serializable {
    private int nbDoors;
    private String color;
    private String plate;
}
