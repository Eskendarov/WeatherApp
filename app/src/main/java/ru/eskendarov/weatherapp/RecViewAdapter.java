package ru.eskendarov.weatherapp;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * @author Enver Eskendarov
 */
final class RecViewAdapter extends RecyclerView.Adapter<RecViewAdapter.CityViewHolder> {

  private final List<Cities> cities;

  RecViewAdapter(final List<Cities> cities) {
    this.cities = cities;
    Log.d(getClass().getSimpleName(), String.format("RecViewAdapter: %s", ""));
  }

  @Override
  public CityViewHolder onCreateViewHolder(
      final ViewGroup viewGroup,
      final int i) {
    final View v = LayoutInflater.from(viewGroup.getContext())
        .inflate(R.layout.frame_cities_list, viewGroup, false);
    Log.d(getClass().getSimpleName(), String.format("onCreateViewHolder: %s", ""));
    return new CityViewHolder(v);
  }

  @Override
  public void onBindViewHolder(final CityViewHolder cityViewHolder, final int i) {
    cityViewHolder.cityName.setText(cities.get(i).getName());
    cityViewHolder.country.setText(cities.get(i).getCountry());
    cityViewHolder.cityPhoto.setImageResource(cities.get(i).getPhotoId());
    Log.d(getClass().getSimpleName(), String.format("onBindViewHolder: %s", ""));
  }

  @Override
  public int getItemCount() {
    Log.d(getClass().getSimpleName(), String.format("getItemCount: %s", ""));
    return cities.size();
  }

  @Override
  public void onAttachedToRecyclerView(@NonNull final RecyclerView recyclerView) {
    super.onAttachedToRecyclerView(recyclerView);
    Log.d(getClass().getSimpleName(), String.format("onAttachedToRecyclerView: %s", ""));
  }

  static class CityViewHolder extends RecyclerView.ViewHolder {

    private final CardView cv;
    private final TextView cityName;
    private final TextView country;
    private final ImageView cityPhoto;

    CityViewHolder(final View itemView) {
      super(itemView);
      cv = itemView.findViewById(R.id.card_view);
      cityName = itemView.findViewById(R.id.city_name);
      country = itemView.findViewById(R.id.country);
      cityPhoto = itemView.findViewById(R.id.city_photo);
      itemView.setOnClickListener(v -> {
        Toast.makeText(itemView.getContext(),
            "CardView #" + (getLayoutPosition() + 1),
            Toast.LENGTH_SHORT).show();
        Log.d(getClass().getSimpleName(), String.format("onClick: %s", ""));
      });
      Log.d(getClass().getSimpleName(), String.format("CityViewHolder: %s", ""));
    }
  }
}