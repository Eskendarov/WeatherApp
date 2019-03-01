package ru.eskendarov.weatherapp.apiweather;

import com.google.gson.annotations.SerializedName;
import java.util.Calendar;
import java.util.List;

/**
 * @author Enver Eskendarov
 */
public final class Weather {

  @SerializedName("main")
  private WeatherTemp temp;
  @SerializedName("weather")
  private List<WeatherDescription> description;
  @SerializedName("name")
  private String city;
  @SerializedName("dt")
  private long timestamp;

  public Calendar getDate() {
    Calendar date = Calendar.getInstance();
    date.setTimeInMillis(timestamp * 1000);
    return date;
  }

  public String getTemp() {
    if (temp.temp.intValue() > 1) {
      return String.format("+%s\u00B0C", temp.temp.intValue());
    } else {
      return String.format("%s\u00B0C", temp.temp.intValue());
    }
  }

  public String getTempWithDegree() {
    return String.valueOf(temp.temp.intValue()) + "\u00B0" + "C";
  }

  public String getCity() {
    return city;
  }

  public String getIconUrl() {
    return "http://openweathermap.org/img/w/" + getIcon() + ".png";
  }

  public String getIcon() {
    return description.get(0).icon;
  }

  public class WeatherTemp {

    Double temp;
    Double temp_min;
    Double temp_max;
  }

  public class WeatherDescription {

    String icon;
  }
}