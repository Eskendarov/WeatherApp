package ru.eskendarov.weatherapp.apiweather;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * @author Enver Eskendarov
 */
public final class WeatherForecast {

  @SerializedName("list")
  private List<Weather> items;

  public WeatherForecast(List<Weather> items) {
    this.items = items;
  }

  public List<Weather> getItems() {
    return items;
  }
}