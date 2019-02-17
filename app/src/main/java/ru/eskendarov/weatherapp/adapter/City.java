package ru.eskendarov.weatherapp.adapter;

import android.util.Log;

/**
 * @author Enver Eskendarov
 */
public final class City {

  private final String name;
  private final String country;
  private final int photoId;

  public City(final String name, final String country, final int photoId) {
    this.name = name;
    this.country = country;
    this.photoId = photoId;
    logging("City");
  }

  private void logging(final String message) {
    Log.d(getClass().getSimpleName(), String.format("%s", message));
  }

  public String getName() {
    logging("getName");
    return name;
  }

  String getCountry() {
    logging("getCountry");
    return country;
  }

  int getPhotoId() {
    logging("getPhotoId");
    return photoId;
  }
}