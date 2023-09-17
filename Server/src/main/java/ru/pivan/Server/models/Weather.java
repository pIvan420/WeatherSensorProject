package ru.pivan.Server.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity
@Table(name = "Weather")
public class Weather {

    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "sensor_name", referencedColumnName = "name")
    @NotNull(message = "Sensor cannot be empty")
    private Sensor sensor;

    @Column(name = "value")
    @NotNull(message = "Temperature cannot be empty")
    @Min(value = -100, message = "Temperature cannot be less than -100")
    @Max(value = 100, message = "Temperature cannot be more than 100")
    private Float value;

    @Column(name = "raining")
    @NotNull(message = "Raining field cannot be empty")
    private Boolean raining;

    @Column(name = "cur_time")
    private LocalDateTime cur_time;

    public Weather() {
    }

    public Weather(Sensor sensor, Float value, Boolean raining) {
        this.sensor = sensor;
        this.value = value;
        this.raining = raining;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }

    public Float getValue() {
        return value;
    }

    public void setValue(Float value) {
        this.value = value;
    }

    public Boolean getRaining() {
        return raining;
    }

    public void setRaining(Boolean raining) {
        this.raining = raining;
    }

    public LocalDateTime getCur_time() {
        return cur_time;
    }

    public void setCur_time(LocalDateTime cur_time) {
        this.cur_time = cur_time;
    }

    @Override
    public String toString() {
        return "Weather{" +
                "id=" + id +
                ", sensor=" + sensor +
                ", value=" + value +
                ", raining=" + raining +
                ", cur_time=" + cur_time +
                '}';
    }
}
