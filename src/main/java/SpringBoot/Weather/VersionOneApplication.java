package SpringBoot.Weather;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import SpringBoot.Weather.Services.GetIP;

@SpringBootApplication
public class VersionOneApplication {

	public static void main(String[] args) {

		GetIP ip = new GetIP();

		System.out.println("Hellow " + ip.getHostname());
		System.out.println(ip.getIp());
	}

}
