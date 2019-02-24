package ru.eskendarov.weatherapp.dbhelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import ru.eskendarov.weatherapp.apiweather.Weather;

/**
 * @author Enver Eskendarov
 */
public final class DatabaseHelper extends SQLiteOpenHelper {

  // region fields
  private static final String DB_NAME = "cities_info.db";
  private static final int VERSION = 1;
  private static final String TABLE_NAME = "cities";
  private static final String ID = "id";
  private static final String CITY_NAME = "city_name";
  private static final String TEMPERATURE = "temperature";
  private static final String ICON_URL = "icon_image";
  private static final String LAST_UPDATE = "last_update";
  private static final String[] WEATHER_COLUMNS =
          {ID, CITY_NAME, TEMPERATURE, ICON_URL, LAST_UPDATE};
  private static final String CREATE_CITIES_TABLE = String.format(
          "create table %s ( " +
                  "%s integer primary key autoincrement, " +
                  "%s TEXT NOT NULL unique, " +
                  "%s TEXT NOT NULL, " +
                  "%s TEXT NOT NULL, " +
                  "%s TEXT NOT NULL);",
          TABLE_NAME, ID, CITY_NAME, TEMPERATURE, ICON_URL, LAST_UPDATE
  );
  private final SimpleDateFormat dateFormat =
          new SimpleDateFormat("EEEE, d MMM, HH:mm");
  // endregion

  public DatabaseHelper(@Nullable final Context context) {
    super(context, DB_NAME, null, VERSION);
  }

  @Override
  public void onCreate(final SQLiteDatabase db) {
    db.execSQL(CREATE_CITIES_TABLE);
  }

  @Override
  public void onUpgrade(final SQLiteDatabase db,
                        final int oldVersion,
                        final int newVersion) {
  }

  public void addCityWeather(final Weather weather, final SQLiteDatabase database) {
    final ContentValues values = new ContentValues();
    values.put(CITY_NAME, weather.getCity());
    values.put(TEMPERATURE, weather.getTemp());
    values.put(ICON_URL, weather.getIconUrl());
    values.put(LAST_UPDATE, dateFormat.format(weather.getDate().getTime()));
    database.insert(TABLE_NAME, null, values);
  }

  public ArrayList<CityWeather> getCityWeatherCities(final SQLiteDatabase database) {
    final ArrayList<CityWeather> weatherCities = new ArrayList<>();
    final Cursor cursor;
    try {
      cursor = database.query(TABLE_NAME, WEATHER_COLUMNS,
              null, null, null, null, null);
      cursor.moveToFirst();
      if (!cursor.isAfterLast()) {
        do {
          final CityWeather cityWeather = new CityWeather();
          cityWeather.setName(cursor.getString(1));
          cityWeather.setTemp(cursor.getString(2));
          cityWeather.setImageIcon(cursor.getString(3));
          cityWeather.setLastUpdate(cursor.getString(4));
          weatherCities.add(cityWeather);
        } while (cursor.moveToNext());
      }
      cursor.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return weatherCities;
  }
}