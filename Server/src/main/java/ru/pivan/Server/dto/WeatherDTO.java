package ru.pivan.Server.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class WeatherDTO {

    @NotNull(message = "Temperature cannot be empty")
    @Min(value = -100, message = "Temperature cannot be less than -100")
    @Max(value = 100, message = "Temperature cannot be more than 100")
    private Float value;

    @NotNull(message = "Raining field cannot be empty")
    private Boolean raining;

    @NotNull(message = "Sensor cannot be empty")
    private SensorDTO sensor;

    public Float getValue() {
        return value;
    }

    public void setValue(Float value) {
        this.value = value;
    }

    public Boolean getRaining() {
        return raining;
    }

    public SensorDTO getSensor() {
        return sensor;
    }

    public void setSensor(SensorDTO sensor) {
        this.sensor = sensor;
    }

    public void setRaining(Boolean raining) {
        this.raining = raining;
    }
}
