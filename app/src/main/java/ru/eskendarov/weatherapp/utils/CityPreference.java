package ru.eskendarov.weatherapp.utils;

import android.app.Activity;
import android.content.SharedPreferences;
import androidx.annotation.NonNull;

/**
 * @author Enver Eskendarov
 */
public final class CityPreference {

  private static final String KEY = "city";
  private final SharedPreferences prefs;

  public CityPreference(@NonNull final Activity activity) {
    prefs = activity.getPreferences(Activity.MODE_PRIVATE);
  }

  // Saint-Petersburg as the default city
  public String getCity() {
    return prefs.getString(KEY, "Saint-Petersburg");
  }

  public void setCity(final String city) {
    prefs.edit().putString(KEY, city).apply();
  }
}