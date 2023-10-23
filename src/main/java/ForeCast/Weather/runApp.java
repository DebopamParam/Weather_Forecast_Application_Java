package ForeCast.Weather;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import ForeCast.Weather.Services.ForeCast.ForcastData;
import ForeCast.Weather.Services.ForeCast.WeatherOfDay;
import ForeCast.Weather.Services.Retrieve_Host_IP_Location.GetHostName;
// import ForeCast.Weather.Services.Retrieve_Host_IP_Location.GetIP;
import ForeCast.Weather.Services.Retrieve_Host_IP_Location.GetLocation;

public class runApp {

	public static void main(String[] args) throws IOException, InterruptedException, URISyntaxException {

		System.out.println("***************************************************************");
		System.out.println("\n            WEATHER FORECAST APPLICATION \n");
		System.out.println("***************************************************************");

		Thread.sleep(1000);

		GetHostName name = new GetHostName();
		System.out.println("\n      Hello " + name.getHostname()
				+ " (⌐■_■) . Let's see where you Live.......(Fetching Your Location)");
		// Thread.sleep(500);
		// GetIP ip = new GetIP();
		// String ipAddress = ip.getIp();
		// System.out.println("Your Public IP Address is : " + ipAddress);

		GetLocation location = new GetLocation();
		location = location.getLocation();
		System.out.println("\n      Your Current Location -->   City : " + location.getCity() + "\t Country : "
				+ location.getCountry_name());
		Thread.sleep(2000);
		System.out.println("\n      Good Place Good Place. Invite me over Dinner Sometime ");
		Thread.sleep(2000);
		System.out.println("\n      Let's See what time of the Week is Suitable for me to Come...... ");
		Thread.sleep(2000);
		System.out.println(
				"\n      Abra Ka Dabra....       (Fetching weather of ---> " + location.getCity() + ","
						+ location.getCountry_name()
						+ ")\n\n");

		ForcastData forecastData = new ForcastData();
		List<WeatherOfDay> weather = forecastData.get_Weather_Data_List();

		System.out.println("\t Date \t\t avgTemperature \t feelsLikeTemperature \t humidity \t Summary");
		System.out.println("\t _____ \t\t ______________ \t ____________________ \t ________ \t _______");
		System.out.println();
		for (WeatherOfDay obj : weather) {
			System.out.println(
					"\t " + obj.getDate() + " \t " + obj.getavgTemperature()
							+ "°C \t\t " + obj.getFeelsLikeTemperature() + "°C \t\t " + obj.getHumidity() + " \t\t "
							+ obj.getSummary());
		}
		System.out.println();
		System.out.println("      Whenever it Doesn't Rain, I am free ;P ");
		System.out.println();

	}

}
