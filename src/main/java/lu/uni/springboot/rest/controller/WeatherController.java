package lu.uni.springboot.rest.controller;

import lu.uni.springboot.rest.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/weather")
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @GetMapping("/by-geolocation")
    public Map<String, Object> getWeatherByGeolocation(
            @RequestParam double latitude,
            @RequestParam double longitude,
            @RequestParam int days) {
        if (days < 1 || days > 10) {
            throw new IllegalArgumentException("The 'days' parameter must be between 1 and 10.");
        }

        double averageTemperature = weatherService.getWeatherByGeolocation(latitude, longitude, days);
        averageTemperature = Math.round(averageTemperature * 100.0) / 100.0;
        Map<String, Object> response = new HashMap<>();
        response.put("average_temperature", averageTemperature);
        response.put("days", days);
        response.put("latitude", latitude);
        response.put("longitude", longitude);
        return response;
    }

    @GetMapping("/by-location-name")
    public Map<String, Object> getWeatherByLocationName(
            @RequestParam String location,
            @RequestParam int days) {
        if (days < 1 || days > 10) {
            throw new IllegalArgumentException("The 'days' parameter must be between 1 and 10.");
        }

        double averageTemperature = weatherService.getWeatherByLocationName(location, days);
        averageTemperature = Math.round(averageTemperature * 100.0) / 100.0;
        Map<String, Object> response = new HashMap<>();
        response.put("average_temperature", averageTemperature);
        response.put("days", days);
        response.put("location", location);
        return response;
    }
}