package ru.pivan.Server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.pivan.Server.models.Weather;
import ru.pivan.Server.repositories.WeatherRepository;

import java.time.LocalDateTime;
import java.util.*;

@Service
@Transactional(readOnly = true)
public class WeatherService {

    private final WeatherRepository weatherRepository;

    @Autowired
    public WeatherService(WeatherRepository weatherRepository) {
        this.weatherRepository = weatherRepository;
    }

    @Transactional
    public void addWeather(Weather weather){
        enrichWeather(weather);
        weatherRepository.save(weather);
    }

    public List<Weather> getAllWeather(){
        return weatherRepository.findAll();
    }

    public int getRainyDayCount(){
        return weatherRepository.findByRaining(true).size();
    }

    private void enrichWeather(Weather weather){
        weather.setCur_time(LocalDateTime.now());
    }
}
