package com.example.starwars.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.starwars.databinding.ItemPlanetBinding;
import com.example.starwars.fragments.HomeFragmentDirections;
import com.example.starwars.models.planet.Planet;

import java.util.ArrayList;
import java.util.List;

public class PlanetAdapter extends RecyclerView.Adapter<PlanetAdapter.ViewHolder> {

    public static final String TAG = "PlanetAdapter";
    private final String IMG_URL = "https://starwars-visualguide.com/assets/img/planets/";
    private final String IMG_ERROR_URL = "https://starwars-visualguide.com/assets/img/placeholder.jpg";

    private Context context;
    private List<Planet> planets;

    public PlanetAdapter(Context context) {
        this.context = context;
        this.planets = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemPlanetBinding itemBinding = ItemPlanetBinding.inflate(layoutInflater, parent, false);

        return new ViewHolder(itemBinding);
    }

    @NonNull
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Planet planet = planets.get(position);
        holder.bind(planet);
    }

    @Override
    public int getItemCount() {
        return planets == null ? 0 : planets.size();
    }

    public void addPlanets(List<Planet> planets) {
        this.planets.clear();
        this.planets.addAll(planets);
        this.notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final ItemPlanetBinding binding;

        public ViewHolder(ItemPlanetBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Planet planet) {

            binding.setPlanet(planet);

            binding.setOnItemClick(new OnItemClickListener() {
                @Override
                public void onItemClick(Planet planet) {
                    Log.d(TAG, "onItemClick planet -> " + planet);
                    navigateToPlanetDetailFragment(planet, binding.getRoot());
                }
            });

            String planetId = planet.getUrl().split("/")[5];
            String currentUrl = IMG_URL + planetId + ".jpg";
            loadImage(binding.getRoot(), currentUrl,  binding.itemImage);

            binding.executePendingBindings();

        }

        public void navigateToPlanetDetailFragment(Planet planet, View view) {
            Bitmap bitmapImage = ((BitmapDrawable) binding.itemImage.getDrawable()).getBitmap();

            HomeFragmentDirections.ActionHomeFragmentToPlanetDetailFragment direction =
                HomeFragmentDirections.actionHomeFragmentToPlanetDetailFragment(planet, bitmapImage);

            Navigation.findNavController(view).navigate(direction);
        }

        public void loadImage(View view, String url, ImageView imageView) {
            Glide
                .with(view)
                .load(url)
                .error(IMG_ERROR_URL)
                .into(imageView);
        }

    }

    public interface OnItemClickListener {
        void onItemClick(Planet planet);
    }

}