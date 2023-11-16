package ForeCast.Weather.Services.ForeCast;


import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import ForeCast.Weather.Services.Retrieve_Host_IP_Location.GetLocation;

public class ForcastData {

    private static final String API_KEY = "Enter your API key";
    private static final String API_BASE_URL = "https://api.openweathermap.org/data/2.5/forecast";

    private JsonObject responseJsonObject;

    public ForcastData() {
        try {
            GetLocation location = new GetLocation();
            location = location.getLocation();

            HttpClient httpClient = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(buildRequestUri(location.getLatitude(), location.getLongitude()))
                    .GET().build();

            HttpResponse<String> response = httpClient.send(request, BodyHandlers.ofString());

            String jsonString = response.body();
            Gson gson = new Gson();
            responseJsonObject = gson.fromJson(jsonString, JsonObject.class);

        } catch (Exception e) {
            // Handle exceptions appropriately
            e.printStackTrace();
        }
    }

    private URI buildRequestUri(double latitude, double longitude) throws URISyntaxException {
        return new URI(API_BASE_URL + "?lat=" + latitude + "&lon=" + longitude + "&appid=" + API_KEY + "&units=metric");
    }

    public List<WeatherOfDay> getWeatherDataList() {
        List<WeatherOfDay> forecast = new ArrayList<>();

        RetrieveDate dateObj = new RetrieveDate();
        JsonArray forecastJsonArray = responseJsonObject.getAsJsonArray("list");

        for (int i = 0; i < forecastJsonArray.size(); i += 8) {
            JsonObject jsonObject = forecastJsonArray.get(i).getAsJsonObject();

            Long dt = jsonObject.getAsJsonPrimitive("dt").getAsLong();
            String dateOfTheDay = dateObj.getDate(dt);

            JsonObject main = jsonObject.getAsJsonObject("main");

            Double avgTemperature = main.getAsJsonPrimitive("temp").getAsDouble();
            Double feelsLikeTemperature = main.getAsJsonPrimitive("feels_like").getAsDouble();
            Double humidity = main.getAsJsonPrimitive("humidity").getAsDouble();

            JsonArray weather = jsonObject.getAsJsonArray("weather");
            JsonObject weatherObject = weather.get(0).getAsJsonObject();

            String summary = weatherObject.getAsJsonPrimitive("description").getAsString();

            WeatherOfDay obj = new WeatherOfDay(dateOfTheDay, avgTemperature, feelsLikeTemperature, humidity, summary);
            forecast.add(obj);
        }

        return forecast;
    }
}

