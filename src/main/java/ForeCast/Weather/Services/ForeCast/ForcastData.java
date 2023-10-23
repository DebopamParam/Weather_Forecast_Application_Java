package ForeCast.Weather.Services.ForeCast;

import java.io.IOException;
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
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import ForeCast.Weather.Services.Retrieve_Host_IP_Location.GetLocation;

public class ForcastData {

    JsonObject response_json_Object;

    public ForcastData() throws URISyntaxException, IOException, InterruptedException {

        GetLocation location = new GetLocation();
        location = location.getLocation();
        final String id = "67b0cdb6af1595fecfc2629063e5d4ef";
        Gson gson = new Gson();

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("https://api.openweathermap.org/data/2.5/forecast?lat=" + location.getLatitude()
                        + "&lon=" + location.getLongitude() + "&appid=" + id + "&units=metric"))
                .GET().build();

        HttpResponse<String> response = httpClient.send(request, BodyHandlers.ofString());

        String json_String = response.body();

        JsonObject jsonObject = gson.fromJson(json_String, JsonObject.class);

        response_json_Object = jsonObject;
    }

    public List<WeatherOfDay> get_Weather_Data_List() {
        List<WeatherOfDay> forecast = new ArrayList<WeatherOfDay>();

        RetrieveDate getDateObj = new RetrieveDate();
        JsonArray jsonArrayOfForecast = response_json_Object.get("list").getAsJsonArray();

        for (int i = 0; i < jsonArrayOfForecast.size(); i += 8) {
            JsonElement json = jsonArrayOfForecast.get(i);
            JsonObject jsonObject = json.getAsJsonObject();

            Long dt = jsonObject.get("dt").getAsLong();
            String date_of_the_day = getDateObj.getDate(dt);

            JsonObject main = jsonObject.get("main").getAsJsonObject();

            Double avgTemperature = main.get("temp").getAsDouble();

            Double feelsLikeTemperature = main.get("feels_like").getAsDouble();

            Double humidity = main.get("humidity").getAsDouble();

            JsonArray summ = jsonObject.get("weather").getAsJsonArray();
            JsonElement summ_json = summ.get(0);
            JsonObject summ_jsonObject = summ_json.getAsJsonObject();

            String summary = summ_jsonObject.get("description").getAsString();

            WeatherOfDay obj = new WeatherOfDay(date_of_the_day, avgTemperature, feelsLikeTemperature, humidity,
                    summary);

            forecast.add(obj);

        }
        return forecast;
    }

}
