package ru.pivan.Server.controllers;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.pivan.Server.dto.WeatherDTO;
import ru.pivan.Server.models.Weather;
import ru.pivan.Server.services.WeatherService;
import ru.pivan.Server.util.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/measurement")
public class WeatherController {

    private final WeatherService weatherService;
    private final ModelMapper modelMapper;
    private final WeatherValidator weatherValidator;

    @Autowired
    public WeatherController(WeatherService weatherService, ModelMapper modelMapper, WeatherValidator weatherValidator) {
        this.weatherService = weatherService;
        this.modelMapper = modelMapper;
        this.weatherValidator = weatherValidator;
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> addWeather(@RequestBody @Valid WeatherDTO weatherDTO,
                                                 BindingResult bindingResult){
        Weather weather = convertToWeather(weatherDTO);

        weatherValidator.validate(weather, bindingResult);

        if(bindingResult.hasErrors()){
            StringBuilder errorMsg = new StringBuilder();

            List<FieldError> errors = bindingResult.getFieldErrors();
            for(FieldError error: errors){
                errorMsg.append(error.getField())
                        .append(" - ").append(error.getDefaultMessage())
                        .append(";");
            }

            throw new WeatherNotRegisteredException(errorMsg.toString());
        }

        weatherService.addWeather(weather);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("")
    public List<WeatherDTO> getAllWeathers(){
        return weatherService.getAllWeather().stream().map(this::convertToWeatherDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/rainyDayCount")
    public Integer getRainyDayCount(){
        return weatherService.getRainyDayCount();
    }

    private WeatherDTO convertToWeatherDTO(Weather weather){
        return modelMapper.map(weather, WeatherDTO.class);
    }

    private Weather convertToWeather(WeatherDTO weatherDTO){
        return modelMapper.map(weatherDTO, Weather.class);
    }

    @ExceptionHandler
    private ResponseEntity<WeatherErrorResponse> handleException(WeatherNotRegisteredException e){
        WeatherErrorResponse response = new WeatherErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST); // Not created
    }
}
