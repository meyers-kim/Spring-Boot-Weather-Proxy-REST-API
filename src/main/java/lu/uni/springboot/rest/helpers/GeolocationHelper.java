package lu.uni.springboot.rest.helpers;

import lu.uni.springboot.rest.models.LocationModel;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
public class GeolocationHelper {

    private final RestTemplate restTemplate = new RestTemplate();

    public LocationModel getGeolocation(String location) {
        String geoApiUrl = String.format(
                "https://nominatim.openstreetmap.org/search?q=%s&format=json&limit=1",
                location);

        Map<String, Object>[] response = restTemplate.getForObject(geoApiUrl, Map[].class);

        if (response != null && response.length > 0) {
            Map<String, Object> locationData = response[0];
            double latitude = Double.parseDouble(locationData.get("lat").toString());
            double longitude = Double.parseDouble(locationData.get("lon").toString());
            return new LocationModel(latitude, longitude);
        } else {
            throw new RuntimeException("Location not found!");
        }
    }
}