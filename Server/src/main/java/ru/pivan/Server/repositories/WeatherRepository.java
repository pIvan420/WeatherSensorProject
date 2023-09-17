package ru.pivan.Server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.pivan.Server.models.Weather;

import java.util.*;

@Repository
public interface WeatherRepository extends JpaRepository<Weather, Integer> {
    List<Weather> findByRaining(Boolean raining);
}
