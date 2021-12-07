package com.example.starwars.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.starwars.databinding.ItemSpecieBinding;
import com.example.starwars.fragments.HomeFragmentDirections;
import com.example.starwars.models.film.Film;
import com.example.starwars.models.specie.Specie;

import java.util.ArrayList;
import java.util.List;

public class SpecieAdapter extends RecyclerView.Adapter<SpecieAdapter.ViewHolder> {

    private final String TAG = "SpecieAdapter";
    private final String IMG_URL = "https://starwars-visualguide.com/assets/img/species/";
    private final String IMG_ERROR_URL = "https://starwars-visualguide.com/assets/img/placeholder.jpg";

    private Context context;
    private List<Specie> species;

    public SpecieAdapter(Context context) {
        this.context = context;
        this.species = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemSpecieBinding itemBinding = ItemSpecieBinding.inflate(layoutInflater, parent, false);

        return new ViewHolder(itemBinding);
    }

    @NonNull
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Specie specie = species.get(position);
        holder.bind(specie);
    }

    @Override
    public int getItemCount() {
        return species == null ? 0 : species.size();
    }

    public void addSpecies(List<Specie> species) {
        this.species.clear();
        this.species.addAll(species);
        this.notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final ItemSpecieBinding binding;

        public ViewHolder(ItemSpecieBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Specie specie) {

            binding.setSpecie(specie);

            binding.setOnItemClick(new OnItemClickListener() {
                @Override
                public void onItemClick(Specie specie) {
                    navigateToSpecieDetailFragment(specie, binding.getRoot());
                }
            });

            String specieId = specie.getUrl().split("/")[5];
            String currentUrl = IMG_URL + specieId + ".jpg";
            loadImage(binding.getRoot(), currentUrl,  binding.itemImage);

            binding.executePendingBindings();

        }

        public void navigateToSpecieDetailFragment(Specie specie, View view) {
            Bitmap bitmapImage = ((BitmapDrawable) binding.itemImage.getDrawable()).getBitmap();

            HomeFragmentDirections.ActionHomeFragmentToSpecieDetailFragment direction =
                HomeFragmentDirections.actionHomeFragmentToSpecieDetailFragment(specie, bitmapImage);

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
        void onItemClick(Specie specie);
    }

}

