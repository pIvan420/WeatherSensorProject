import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.XYSeries;
import org.knowm.xchart.style.markers.SeriesMarkers;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();
        Random random = new Random();

        String url1 = "http://localhost:8080/sensor/registration";
        String url2 = "http://localhost:8080/measurement/add";
        String url3 = "http://localhost:8080/measurement";
        String sensorName = "sensor1";

        Sensor sensor = new Sensor();
        sensor.setName("sensor123");

//        HttpEntity<Sensor> request = new HttpEntity<>(sensor);
//
//        String response = restTemplate.postForObject(url1, request, String.class);
//
//        System.out.println(response);
//
//        float minValue = -100.0f;
//        float maxValue = 100.0f;
//        Weather weather = new Weather();
//        weather.setSensor(sensor);
//
//        for(int i = 0; i < 1000; i++){
//            weather.setValue(Math.round((minValue + random.nextFloat() * (maxValue - minValue)) * 10.0) / 10.0f);
//            weather.setRaining(random.nextBoolean());
//            restTemplate.postForObject(url2, new HttpEntity<>(weather), String.class);
//        }

        Weather[] weathers = restTemplate.getForObject(url3, Weather[].class);

        List<Float> temp = Arrays.stream(weathers)
                .map(Weather::getValue)
                .collect(Collectors.toList());

        List<XYChart> charts = new ArrayList<XYChart>();
        XYChart chart = new XYChartBuilder().xAxisTitle("Time").yAxisTitle("Temperature").width(600).height(400).build();
        XYSeries series = chart.addSeries("Weathers", null, temp);
        series.setMarker(SeriesMarkers.NONE);
        charts.add(chart);
        new SwingWrapper<>(charts).displayChartMatrix();
    }
}
