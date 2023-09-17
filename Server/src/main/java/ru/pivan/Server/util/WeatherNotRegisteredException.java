package ru.pivan.Server.util;

public class WeatherNotRegisteredException extends RuntimeException{
    public WeatherNotRegisteredException(String msg){
        super(msg);
    }
}
