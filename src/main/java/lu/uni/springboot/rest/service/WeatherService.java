package lu.uni.springboot.rest.service;

import lu.uni.springboot.rest.helpers.GeolocationHelper;
import lu.uni.springboot.rest.models.LocationModel;
import lu.uni.springboot.rest.models.WeatherResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
public class WeatherService {

    @Autowired
    private GeolocationHelper geolocationHelper;

    private final RestTemplate restTemplate = new RestTemplate();

    public double getWeatherByGeolocation(double latitude, double longitude, int days) {
        String weatherApiUrl = String.format(
                "https://api.open-meteo.com/v1/forecast?latitude=%f&longitude=%f&daily=temperature_2m_max&timezone=auto",
                latitude, longitude);

        WeatherResponseModel response = restTemplate.getForObject(weatherApiUrl, WeatherResponseModel.class);
        if (response != null && response.getDaily() != null) {
            List<Double> temperatures = (List<Double>) response.getDaily().get("temperature_2m_max");
            double sum = 0;
            for (int i = 0; i < days && i < temperatures.size(); i++) {
                sum += temperatures.get(i);
            }
            return sum / days;
        } else {
            throw new RuntimeException("Failed to fetch weather data");
        }
    }

    public double getWeatherByLocationName(String location, int days) {
        LocationModel coordinates = geolocationHelper.getGeolocation(location);
        return getWeatherByGeolocation(coordinates.getLatitude(), coordinates.getLongitude(), days);
    }
}