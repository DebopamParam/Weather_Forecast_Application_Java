package SpringBoot.Weather.Services;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class GetIP {

    private String ip = "", hostname = "";

    public GetIP() {
        try {
            InetAddress localhost = InetAddress.getLocalHost();
            ip = (String) localhost.getHostAddress();
            hostname = (String) localhost.getHostName();

        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    public String getIp() {
        return ip;
    }

    public String getHostname() {
        return hostname;
    }

}
