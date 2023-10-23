package ForeCast.Weather.Services.Retrieve_Host_IP_Location;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

public class GetIP {
    String ip;

    public GetIP() throws IOException, InterruptedException, URISyntaxException {
        HttpRequest request = HttpRequest.newBuilder().uri(new URI("https://ipecho.net/plain")).GET().build();

        HttpClient httpClient = HttpClient.newHttpClient();

        HttpResponse<String> response = httpClient.send(request, BodyHandlers.ofString());

        ip = response.body();
    }

    public String getIp() {
        return ip;
    }

}
