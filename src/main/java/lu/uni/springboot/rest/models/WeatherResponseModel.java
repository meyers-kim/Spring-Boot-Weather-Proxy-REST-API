package lu.uni.springboot.rest.models;

import java.util.Map;

public class WeatherResponseModel {
    private Map<String, Object> daily;

    public Map<String, Object> getDaily() {
        return daily;
    }

    public void setDaily(Map<String, Object> daily) {
        this.daily = daily;
    }
}