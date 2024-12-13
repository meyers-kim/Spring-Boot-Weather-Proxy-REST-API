package lu.uni.springboot.rest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
public class LocalhostController {

    @GetMapping("/")
    public Map<String, String> handleRootRequest() {
        Map<String, String> response = new LinkedHashMap<>();
        response.put("Usage", "Use the following endpoints:");
        response.put("geolocation_endpoint", "/api/weather/by-geolocation?latitude=LAT&longitude=LON&days=DAYS");
        response.put("Example geolocation", "/api/weather/by-geolocation?latitude=49.5006&longitude=5.9461&days=3");
        response.put("location_endpoint", "/api/weather/by-location-name?location=LOCATION&days=DAYS");
        response.put("Example location name", "/api/weather/by-location-name?location=Esch-Belval&days=3");
        return response;
    }
}