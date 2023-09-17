package ru.pivan.Server.util;

public class SensorNotRegisteredException extends RuntimeException{
    public SensorNotRegisteredException(String msg){
        super(msg);
    }
}
