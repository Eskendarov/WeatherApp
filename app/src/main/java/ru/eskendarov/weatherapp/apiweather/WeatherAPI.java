package ru.eskendarov.weatherapp.apiweather;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @author Enver Eskendarov
 */
public final class WeatherAPI {

  public static String KEY = "2ce32912fcd6107310f3f355e888bc48";
  private static final String BASE_URL = "https://api.openweathermap.org/";
  private static Retrofit retrofit = null;

  public static Retrofit getClient() {
    if (retrofit == null) {
      retrofit = new Retrofit.Builder()
              .baseUrl(BASE_URL)
              .addConverterFactory(GsonConverterFactory.create())
              .build();
    }
    return retrofit;
  }

  public interface ApiInterface {

    @GET("data/2.5/weather")
    Call<Weather> getWeatherByCityName(
            @Query("q") String name,
            @Query("units") String units,
            @Query("appid") String appid
    );
  }
}