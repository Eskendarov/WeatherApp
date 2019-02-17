package ru.eskendarov.weatherapp;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import java.util.Objects;

/**
 * @author Enver Eskendarov
 */
public final class WeatherInfoActivity extends AppCompatActivity {

  @Override
  protected void onCreate(@Nullable final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);
    initialize();
    WeatherInfoFragment fragment = new WeatherInfoFragment();
    Bundle bundle = new Bundle();
    bundle.putString("city", getIntent().getStringExtra("city"));
    fragment.setArguments(bundle);
    getSupportFragmentManager()
            .beginTransaction()
            .replace(R.id.container, new WeatherInfoFragment(), "fragment")
            .commit();
  }

  private void initialize() {
    ((AppCompatTextView) findViewById(R.id.info_text))
            .setText(getIntent().getStringExtra("city"));
  }
}