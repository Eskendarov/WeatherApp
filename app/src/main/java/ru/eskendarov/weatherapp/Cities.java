package ru.eskendarov.weatherapp;

import android.util.Log;

/**
 * @author Enver Eskendarov
 */

final class Cities {

  private final String name;
  private final String country;
  private final int photoId;

  Cities(final String name, final String country, final int photoId) {
    this.name = name;
    this.country = country;
    this.photoId = photoId;
    Log.d(getClass().getSimpleName(), String.format("Cities: %s", ""));
  }

  String getName() {
    Log.d(getClass().getSimpleName(), String.format("getName: %s", ""));
    return name;
  }

  String getCountry() {
    Log.d(getClass().getSimpleName(), String.format("getCountry: %s", ""));
    return country;
  }

  int getPhotoId() {
    Log.d(getClass().getSimpleName(), String.format("getPhotoId: %s", ""));
    return photoId;
  }
}