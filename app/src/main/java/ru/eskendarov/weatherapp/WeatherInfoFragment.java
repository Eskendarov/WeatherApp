package ru.eskendarov.weatherapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public final class WeatherInfoFragment extends Fragment {

  @Override
  public View onCreateView(@NonNull final LayoutInflater inflater,
                           @Nullable final ViewGroup container,
                           final Bundle savedInstanceState) {
    final View view =
            inflater.inflate(R.layout.fragment_weather_info, container, false);
    return view;
  }

  @Override
  public void onActivityCreated(final Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
  }
}