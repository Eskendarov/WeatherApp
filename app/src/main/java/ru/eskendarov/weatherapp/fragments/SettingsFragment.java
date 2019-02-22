package ru.eskendarov.weatherapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import ru.eskendarov.weatherapp.R;
import ru.eskendarov.weatherapp.utils.CityPreference;

/**
 * @author Enver Eskendarov
 */
public final class SettingsFragment extends Fragment {

  @BindView(R.id.setting_city_name)
  EditText editText;
  @BindView(R.id.current_city)
  TextView textView;
  @BindView(R.id.save_city_name)
  Button button;
  private CityPreference cityPreference;

  @Override
  public View onCreateView(@NonNull final LayoutInflater inflater,
                           @Nullable final ViewGroup container,
                           @Nullable final Bundle savedInstanceState) {
    final View view = getLayoutInflater()
            .inflate(R.layout.fragment_settings, container, false);
    ButterKnife.bind(this, view);
    cityPreference = new CityPreference(getActivity());
    textView.setText(String.format("Current Default City: %s", cityPreference.getCity()));
    return view;
  }

  @Override
  public void onActivityCreated(@Nullable final Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    button.setOnClickListener(v -> {
      final String cityName = editText.getText().toString();
      if (cityName.length() != 0) {
        cityPreference.setCity(cityName);
        Toast.makeText(getActivity(),
                String.format("saved: %s", cityName), Toast.LENGTH_SHORT).show();
        textView.setText(String.format("Current Default City: %s",
                cityPreference.getCity()));
      } else {
        Toast.makeText(getActivity(),
                "input city name", Toast.LENGTH_SHORT).show();
      }
    });
  }
}
