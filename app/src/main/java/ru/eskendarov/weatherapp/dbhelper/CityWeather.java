package ru.eskendarov.weatherapp.dbhelper;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Enver Eskendarov
 */
@Getter
@Setter
public final class CityWeather {

  private String name;
  private String temp;
  private String imageIcon;
  private String lastUpdate;
}