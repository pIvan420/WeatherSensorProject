package ru.pivan.Server.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.pivan.Server.models.Weather;
import ru.pivan.Server.services.SensorService;

@Component
public class WeatherValidator implements Validator {

    private final SensorService sensorService;

    @Autowired
    public WeatherValidator(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Weather.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Weather weather = (Weather) target;

        if(sensorService.show(weather.getSensor().getName()).isEmpty()){
            errors.rejectValue("sensor", "", "This sensor is not registered in the database");
        }
    }
}
