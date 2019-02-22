package ru.eskendarov.weatherapp.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import ru.eskendarov.weatherapp.R;

/**
 * @author Enver Eskendarov
 */
public final class RecycleViewAdapter extends
        RecyclerView.Adapter<RecycleViewAdapter.CityViewHolder> {

  private static final String TAG = "adapter";
  private final List<City> cities;
  private OnItemClickListener itemClickListener;

  public RecycleViewAdapter(final List<City> cities) {
    this.cities = cities;
    logging("RecycleViewAdapter");
  }

  private void logging(final String message) {
    Log.d(TAG, String.format("%s", message));
  }

  @Override
  public CityViewHolder onCreateViewHolder(final ViewGroup viewGroup,
                                           final int i) {
    final View v = LayoutInflater.from(viewGroup.getContext())
            .inflate(R.layout.card_view_city, viewGroup, false);
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

  public void SetOnItemClickListener(OnItemClickListener itemClickListener) {
    this.itemClickListener = itemClickListener;
  }

  public interface OnItemClickListener {

    void onItemClick(final View view, final String cityName);
  }

  class CityViewHolder extends RecyclerView.ViewHolder {

    private final TextView cityName;
    private final TextView country;
    private final ImageView cityPhoto;

    CityViewHolder(final View itemView) {
      super(itemView);
      cityName = itemView.findViewById(R.id.city_name);
      country = itemView.findViewById(R.id.country);
      cityPhoto = itemView.findViewById(R.id.city_photo);
      itemView.setOnClickListener(v ->
              itemClickListener.onItemClick(v, cityName.getText().toString()));
    }
  }
}