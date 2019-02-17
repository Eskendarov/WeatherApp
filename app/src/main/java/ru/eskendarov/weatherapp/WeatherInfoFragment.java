package ru.eskendarov.weatherapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;

public final class WeatherInfoFragment extends Fragment {

  @Override
  public View onCreateView(@NonNull final LayoutInflater inflater,
                           @Nullable final ViewGroup container,
                           final Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_weather_info, container, false);
  }


  @Override
  public void onActivityCreated(@Nullable final Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    ((AppCompatTextView) getActivity().findViewById(R.id.info_text))
            .setText(getArguments().getString("city"));
    onBackPressed();
  }

  private void onBackPressed() {
    new Activity().onBackPressed();
  }
}