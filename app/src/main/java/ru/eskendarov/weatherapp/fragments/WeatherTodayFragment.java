package ru.eskendarov.weatherapp.fragments;

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
import com.bumptech.glide.Glide;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.eskendarov.weatherapp.R;
import ru.eskendarov.weatherapp.apiweather.Weather;
import ru.eskendarov.weatherapp.apiweather.WeatherAPI;
import ru.eskendarov.weatherapp.utils.CityPreference;

/**
 * @author Enver Eskendarov
 */
public final class WeatherTodayFragment extends Fragment {

  private static final String TAG = "today";
  private final String key = WeatherAPI.KEY;
  private final String units = "metric";
  @BindView(R.id.weather_city_name)
  TextView cityName;
  @BindView(R.id.temperature)
  TextView tempText;
  @BindView(R.id.weather_icon)
  ImageView imageView;
  private WeatherAPI.ApiInterface api;

  @Override
  public View onCreateView(@NonNull final LayoutInflater inflater,
                           @Nullable final ViewGroup container,
                           @Nullable final Bundle savedInstanceState) {
    View view = getLayoutInflater()
            .inflate(R.layout.fragment_weather_today, container, false);
    ButterKnife.bind(this, view);
    api = WeatherAPI.getClient().create(WeatherAPI.ApiInterface.class);
    return view;
  }

  @Override
  public void onActivityCreated(@Nullable final Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    getWeatherByCityName();
    Log.d(TAG, "onActivityCreated() returned: " + savedInstanceState);
  }

  private void getWeatherByCityName() {
    String city;
    if (getArguments() != null) {
      city = getArguments().getString("city");
    } else {
      city = new CityPreference(getActivity()).getCity();
    }
    Call<Weather> weatherCall = api.getWeatherByCityName(city, units, key);
    weatherCall.enqueue(new Callback<Weather>() {
      @Override
      public void onResponse(@NonNull final Call<Weather> call,
                             @NonNull final Response<Weather> response) {
        final Weather data = response.body();
        if (response.isSuccessful()) {
          cityName.setText(data.getCity());
          tempText.setText(data.getTempWithDegree());
          Glide.with(getActivity()).load(data.getIconUrl()).into(imageView);
          Log.d(TAG, "getCity() returned: " + data.getCity());
        } else {
          cityName.setText("Incorrect Value");
          tempText.setTextSize(50f);
          tempText.setText("N/A");
        }
        Log.d(TAG, response.toString());
      }

      @Override
      public void onFailure(@NonNull final Call<Weather> call,
                            @NonNull final Throwable t) {
        Log.d(TAG, "onFailure() returned: " + t.getMessage());
      }
    });
  }
}