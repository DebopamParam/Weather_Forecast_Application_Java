package ForeCast.Weather.Services.ForeCast;

public class WeatherOfDay {
    private String date;
    private Double avgTemperature;
    private Double feelsLikeTemperature;
    private Double humidity;
    private String summary;

    @Override
    public String toString() {
        return "WeatherOfDay [date=" + date + ", avgTemperature=" + avgTemperature + ", feelsLikeTemperature="
                + feelsLikeTemperature + ", humidity=" + humidity + ", summary=" + summary + "]";
    }

    public WeatherOfDay(String date, Double avgTemperature, Double feelsLikeTemperature, Double humidity,
            String summary) {
        this.date = date;
        this.avgTemperature = avgTemperature;
        this.feelsLikeTemperature = feelsLikeTemperature;
        this.humidity = humidity;
        this.summary = summary;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Double getavgTemperature() {
        return avgTemperature;
    }

    public void setavgTemperature(Double avgTemperature) {
        this.avgTemperature = avgTemperature;
    }

    public Double getFeelsLikeTemperature() {
        return feelsLikeTemperature;
    }

    public void setFeelsLikeTemperature(Double feelsLikeTemperature) {
        this.feelsLikeTemperature = feelsLikeTemperature;
    }

    public Double getHumidity() {
        return humidity;
    }

    public void setHumidity(Double humidity) {
        this.humidity = humidity;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

}
