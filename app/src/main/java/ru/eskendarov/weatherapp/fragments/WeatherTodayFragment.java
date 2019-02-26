package ru.eskendarov.weatherapp.fragments;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.bumptech.glide.Glide;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.eskendarov.weatherapp.R;
import ru.eskendarov.weatherapp.apiweather.Weather;
import ru.eskendarov.weatherapp.apiweather.WeatherAPI;
import ru.eskendarov.weatherapp.dbhelper.DatabaseHelper;
import ru.eskendarov.weatherapp.utils.CityPreference;

/**
 * @author Enver Eskendarov
 */
public final class WeatherTodayFragment extends Fragment {

  // region fields
  private static final String TAG = "today";
  private final String key = WeatherAPI.KEY;
  private final String units = "metric";
  @BindView(R.id.weather_city_name)
  TextView cityName;
  @BindView(R.id.temperature)
  TextView tempText;
  @BindView(R.id.weather_icon)
  ImageView imageView;
  @BindView(R.id.last_update)
  TextView lastUpdate;
  private WeatherAPI.ApiInterface api;
  private Call<Weather> weatherCall;
  private Unbinder unbinder;
  private DatabaseHelper databaseHelper;
  private SQLiteDatabase cityDatabase;
  // endregion

  @Override
  public View onCreateView(@NonNull final LayoutInflater inflater,
                           @Nullable final ViewGroup container,
                           @Nullable final Bundle savedInstanceState) {
    View view = getLayoutInflater()
            .inflate(R.layout.fragment_weather_today, container, false);
    unbinder = ButterKnife.bind(this, view);
    api = WeatherAPI.getClient().create(WeatherAPI.ApiInterface.class);
    initDataBase();
    return view;
  }

  private void initDataBase() {
    databaseHelper = new DatabaseHelper(getActivity());
    cityDatabase = databaseHelper.getWritableDatabase();
  }

  @Override
  public void onActivityCreated(@Nullable final Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    getWeatherByCityName();
    Log.d(TAG, "onActivityCreated() returned: " + savedInstanceState);
  }

  @Override
  public void onDestroy() {
    cityDatabase.close();
    unbinder.unbind();
    weatherCall.cancel();
    super.onDestroy();
  }

  private void getWeatherByCityName() {
    String currentCity;
    if (getArguments() != null) {
      currentCity = getArguments().getString("city");
    } else {
      currentCity = new CityPreference(getActivity()).getCity();
    }
    weatherCall = api.getWeatherByCityName(currentCity, units, key);
    weatherCall.enqueue(new Callback<Weather>() {
      @Override
      public void onResponse(@NonNull final Call<Weather> call,
                             @NonNull final Response<Weather> response) {
        final Weather weather = response.body();
        if (response.isSuccessful()) {
          databaseHelper.addCityWeather(weather, cityDatabase);
          cityName.setText(weather.getCity());
          tempText.setText(weather.getTemp());
          Glide.with(getActivity()).load(weather.getIconUrl()).into(imageView);
          imageView.setVisibility(View.VISIBLE);
          Log.d(TAG, "getIconUrl() returned: " + weather.getIconUrl());
        } else {
          cityName.setText("Incorrect Value: " + currentCity);
          tempText.setTextSize(50f);
          tempText.setText("N/A");
        }
        Log.d(TAG, response.toString());
      }

      @Override
      public void onFailure(@NonNull final Call<Weather> call,
                            @NonNull final Throwable t) {
        databaseHelper.getCityWeatherCities(cityDatabase).forEach(cityWeather -> {
          if (cityWeather.getName().equalsIgnoreCase(currentCity)) {
            imageView.setVisibility(View.VISIBLE);
            cityName.setText(cityWeather.getName());
            tempText.setText(cityWeather.getTemp());
            Glide.with(getActivity())
                    .load(cityWeather.getImageIcon()).into(imageView);
            lastUpdate.setVisibility(View.VISIBLE);
            lastUpdate.setText(String.format("Last Update Weather: %s",
                    cityWeather.getLastUpdate()));
          }
        });
        Log.d(TAG, "onFailure() returned: " + t.getMessage());
      }
    });
  }
}