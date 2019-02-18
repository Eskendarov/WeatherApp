package ru.eskendarov.weatherapp.fragments;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindArray;
import butterknife.BindView;
import butterknife.ButterKnife;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import ru.eskendarov.weatherapp.R;
import ru.eskendarov.weatherapp.adapter.City;
import ru.eskendarov.weatherapp.adapter.RecycleViewAdapter;

/**
 * @author Enver Eskendarov
 */
public final class CitiesListFragment extends Fragment {

  @BindView(R.id.recycler_view)
  RecyclerView recyclerView;
  @BindArray(R.array.cities)
  String[] cities;
  @BindArray(R.array.countries)
  String[] countries;
  @BindArray(R.array.cities_imgs)
  TypedArray imageIndex;

  @Override
  public View onCreateView(@NonNull final LayoutInflater inflater,
                           @Nullable final ViewGroup container,
                           @Nullable final Bundle savedInstanceState) {
    final View view =
            inflater.inflate(R.layout.fragment_list_cities, container, false);
    ButterKnife.bind(this, view);
    return view;
  }

  @Override
  public void onActivityCreated(final Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    recyclerView.setHasFixedSize(true);
    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    final RecycleViewAdapter adapter = new RecycleViewAdapter(getCitiesList());
    adapter.SetOnItemClickListener((view, cityName) -> {
      recycleViewClick(cityName);
    });
    recyclerView.setAdapter(adapter);
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

  private void recycleViewClick(final String cityName) {
    final Bundle args = new Bundle();
    args.putString("city", cityName);
    final WeatherInfoFragment infoFragment = new WeatherInfoFragment();
    infoFragment.setArguments(args);
    final FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
    fragmentManager.popBackStack();
    fragmentManager.beginTransaction()
            .addToBackStack(null)
            .replace(R.id.container, infoFragment)
            .commit();
  }
}