package ru.eskendarov.weatherapp.adapter;

import lombok.Data;

/**
 * @author Enver Eskendarov
 */
@Data
public final class City {

  private final String name;
  private final String country;
  private final int photoId;
}