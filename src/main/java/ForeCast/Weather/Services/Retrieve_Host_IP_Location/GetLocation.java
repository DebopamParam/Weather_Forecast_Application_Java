package ForeCast.Weather.Services.Retrieve_Host_IP_Location;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import com.google.gson.Gson;

public class GetLocation {

    private String city;
    private String country_name;
    private Double latitude;
    private Double longitude;

    private static final HttpClient httpClient = HttpClient.newHttpClient();
    private static final Gson gson = new Gson();
    private static final String API_KEY = "kasosC3TdrjLRLfLqL5L2LCzZT5r9Yuq"; // Replace with your actual API key

    public GetLocation getLocation() {
        try {
            GetIP ip = new GetIP();
            String ipAddress = ip.getIp();

            URI requestUri = new URI("https://api.apilayer.com/ip_to_location/" + ipAddress);
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(requestUri)
                    .header("apikey", API_KEY)
                    .GET().build();

            HttpResponse<String> response = httpClient.send(request, BodyHandlers.ofString());
            return gson.fromJson(response.body(), GetLocation.class);

        } catch (IOException | InterruptedException | URISyntaxException e) {
            // Handle exceptions appropriately
            e.printStackTrace();
            // Consider logging the exception or throwing a custom exception
        }

        return null; // or throw an exception indicating failure
    }

    public String getCity() {
        return city;
    }

    public String getCountry_name() {
        return country_name;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    @Override
    public String toString() {
        return "GetLocation [city=" + city + ", country_name=" + country_name + ", latitude=" + latitude
                + ", longitude=" + longitude + "]";
    }

}