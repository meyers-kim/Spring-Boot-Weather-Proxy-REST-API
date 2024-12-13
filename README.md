# Spring Boot Weather Proxy

This Spring Boot application acts as a proxy to fetch weather data for a specified geolocation or location name. The application uses Open-Meteo and OpenStreetMap APIs.

## Endpoints

1. `/api/weather/by-geolocation`
   - **Method**: GET
   - **Parameters**:
     - `latitude` (double): Latitude of the location (e.g., 49.5006 for Esch-Belval).
     - `longitude` (double): Longitude of the location (e.g., 5.9461 for Esch-Belval).
     - `days` (int, 1-10): Number of days for which to calculate the daily average temperature.
   - **Description**: Returns the daily average temperature for the given geolocation.
   - **Example**:
      ```bash
      http://localhost:8080/api/weather/by-geolocation?latitude=49.5006&longitude=5.9461&days=3
      ```

2. `/api/weather/by-location-name`
   - **Method**: GET
   - **Parameters**:
     - `location` (String): Name of the location (e.g., "Esch-Belval").
     - `days` (int, 1-10): Number of days for which to calculate the daily average temperature.
   - **Description**: Converts the location name into geolocation and fetches the weather.
   - **Example**:
      ```bash
      http://localhost:8080/api/weather/by-location-name?location=Esch-Belval&days=3
      ```

## Running the Application

1. Build the application using Maven:
   ```bash
   mvn clean install
   ```
2. Run the Application:
   ```bash
   mvn spring-boot:run
   ```
3. Access the Endpoints:
   Use a browser or Postman to test the endpoints at http://localhost:8080.