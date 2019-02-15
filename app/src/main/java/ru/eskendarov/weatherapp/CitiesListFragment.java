package ru.eskendarov.weatherapp;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindArray;
import butterknife.BindView;
import butterknife.ButterKnife;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CitiesListFragment extends Fragment {

  private final static String TAG = "qwerty";
  @BindView(R.id.recycler_view)
  RecyclerView recyclerView;
  @BindArray(R.array.cities)
  String[] cities;
  @BindArray(R.array.countries)
  String[] countries;
  @BindArray(R.array.cities_imgs)
  TypedArray imageIndex;

  @Override
  public View onCreateView(
          @NonNull final LayoutInflater inflater,
          @Nullable final ViewGroup container,
          final Bundle savedInstanceState) {
    final View view =
            inflater.inflate(R.layout.fragment_list_cities, container, false);
    ButterKnife.bind(this, view);
    return view;
  }

  @Override
  public void onActivityCreated(final Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    recyclerView.setHasFixedSize(true);
    final RecycleViewAdapter adapter = new RecycleViewAdapter(getCitiesList());
    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    recyclerView.setAdapter(adapter);
    adapter.SetOnItemClickListener((view, cityName) -> {
      final Intent intent =
              new Intent(getActivity(), WeatherInfoActivity.class);
      intent.putExtra("city", cityName);
      startActivity(intent);
      Log.d(TAG, cityName);
      Toast.makeText(getActivity(), cityName, Toast.LENGTH_SHORT).show();
    });
  }

  private List<City> getCitiesList() {
    final List<City> cityList = new ArrayList<>();
    for (int i = 0; i < cities.length; i++) {
      cityList.add(new City(cities[i],
              countries[i], imageIndex.getResourceId(i, -1)));
    }
    cityList.sort(Comparator.comparing(City::getName));
    return cityList;
  }
}