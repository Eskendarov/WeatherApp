package ru.eskendarov.weatherapp;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * @author Enver Eskendarov
 * @date 02/02/2019
 */

final class RVAdapter extends RecyclerView.Adapter<RVAdapter.CityViewHolder> {

    private final List<Cities> cities;

    RVAdapter(final List<Cities> cities) {
        this.cities = cities;
    }

    @Override
    public CityViewHolder onCreateViewHolder(final ViewGroup viewGroup, final int i) {
        final View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.frame_cities_list, viewGroup, false);
        return new CityViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final CityViewHolder cityViewHolder, final int i) {
        cityViewHolder.cityName.setText(cities.get(i).getName());
        cityViewHolder.country.setText(cities.get(i).getCountry());
        cityViewHolder.cityPhoto.setImageResource(cities.get(i).getPhotoId());
    }

    @Override
    public int getItemCount() {
        return cities.size();
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull final RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
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
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {
                    Toast.makeText(itemView.getContext(),
                            "CardView #" + (getLayoutPosition() + 1),
                            Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

}