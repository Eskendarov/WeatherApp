package ru.eskendarov.weatherapp;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * @author Enver Eskendarov
 */
final class RecycleViewAdapter extends
        RecyclerView.Adapter<RecycleViewAdapter.CityViewHolder> {

  private final List<City> cities;

  RecycleViewAdapter(final List<City> cities) {
    this.cities = cities;
    logging("RecycleViewAdapter");
  }

  private void logging(final String message) {
    Log.d(getClass().getSimpleName(), String.format("%s", message));
  }

  @Override
  public CityViewHolder onCreateViewHolder(final ViewGroup viewGroup,
                                           final int i) {
    final View v = LayoutInflater.from(viewGroup.getContext())
            .inflate(R.layout.frame_cities_list, viewGroup, false);
    logging("onCreateViewHolder");
    return new CityViewHolder(v);
  }

  @Override
  public void onBindViewHolder(final CityViewHolder cityViewHolder, final int i) {
    cityViewHolder.cityName.setText(cities.get(i).getName());
    cityViewHolder.country.setText(cities.get(i).getCountry());
    cityViewHolder.cityPhoto.setImageResource(cities.get(i).getPhotoId());
    logging("onBindViewHolder");
  }

  @Override
  public int getItemCount() {
    logging("getItemCount");
    return cities.size();
  }

  @Override
  public void onAttachedToRecyclerView(@NonNull final RecyclerView recyclerView) {
    super.onAttachedToRecyclerView(recyclerView);
    logging("onAttachedToRecyclerView");
  }

  static class CityViewHolder extends RecyclerView.ViewHolder {

    private final TextView cityName;
    private final TextView country;
    private final ImageView cityPhoto;

    CityViewHolder(final View itemView) {
      super(itemView);
      cityName = itemView.findViewById(R.id.city_name);
      country = itemView.findViewById(R.id.country);
      cityPhoto = itemView.findViewById(R.id.city_photo);
      itemView.setOnClickListener(v -> {
        Toast.makeText(itemView.getContext(),
                "CardView #" + (getLayoutPosition() + 1),
                Toast.LENGTH_SHORT).show();
        Log.d("click", "onClick: %s");
      });
    }
  }
}