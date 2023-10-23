package ForeCast.Weather.Services.Retrieve_Host_IP_Location;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class GetHostName {

    private String hostname = "";

    public GetHostName() {
        try {
            InetAddress localhost = InetAddress.getLocalHost();
            hostname = (String) localhost.getHostName();

        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    public String getHostname() {
        return hostname;
    }

}
