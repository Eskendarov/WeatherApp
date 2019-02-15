package ru.eskendarov.weatherapp;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import java.util.Objects;

public class WeatherInfoActivity extends AppCompatActivity {

  @Override
  protected void onCreate(@Nullable final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);
//    setContentView(R.layout.fragment_weather_info);
    setContentView(new CView(this));
//    initialize();
  }

  private void initialize() {
    ((AppCompatTextView) findViewById(R.id.info_text))
            .setText(getIntent().getStringExtra("city"));
  }
}