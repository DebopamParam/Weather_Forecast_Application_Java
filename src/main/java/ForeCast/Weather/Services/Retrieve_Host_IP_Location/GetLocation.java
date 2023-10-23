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

    public GetLocation getLocation() throws IOException, InterruptedException, URISyntaxException {

        GetLocation location = new GetLocation();
        HttpClient httpClient = HttpClient.newHttpClient();
        GetIP ip = new GetIP();
        Gson gson = new Gson();

        String ipAddress = ip.getIp();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("https://api.apilayer.com/ip_to_location/" + ipAddress))
                .header("apikey", "kasosC3TdrjLRLfLqL5L2LCzZT5r9Yuq")
                .GET().build();

        HttpResponse<String> response = httpClient.send(request, BodyHandlers.ofString());
        location = gson.fromJson(response.body(), GetLocation.class);

        return location;
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
