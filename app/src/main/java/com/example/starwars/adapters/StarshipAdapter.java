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
import com.example.starwars.databinding.ItemStarshipBinding;
import com.example.starwars.fragments.HomeFragmentDirections;
import com.example.starwars.models.specie.Specie;
import com.example.starwars.models.starship.Starship;

import java.util.ArrayList;
import java.util.List;

public class StarshipAdapter extends RecyclerView.Adapter<StarshipAdapter.ViewHolder> {

    private final String TAG = "StarshipAdapter";
    private final String IMG_URL = "https://starwars-visualguide.com/assets/img/starships/";
    private final String IMG_ERROR_URL = "https://starwars-visualguide.com/assets/img/placeholder.jpg";

    private Context context;
    private List<Starship> starships;

    public StarshipAdapter(Context context) {
        this.context = context;
        this.starships = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemStarshipBinding itemBinding = ItemStarshipBinding.inflate(layoutInflater, parent, false);

        return new ViewHolder(itemBinding);
    }

    @NonNull
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Starship starship = starships.get(position);
        holder.bind(starship);
    }

    @Override
    public int getItemCount() {
        return starships == null ? 0 : starships.size();
    }

    public void addStarships(List<Starship> starships) {
        this.starships.clear();
        this.starships.addAll(starships);
        this.notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final ItemStarshipBinding binding;

        public ViewHolder(ItemStarshipBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Starship starship) {

            binding.setStarship(starship);

            binding.setOnItemClick(new OnItemClickListener() {
                @Override
                public void onItemClick(Starship starship) {
                    navigateToStarshipDetailFragment(starship, binding.getRoot());
                }
            });

            String starshipId = starship.getUrl().split("/")[5];
            String currentUrl = IMG_URL + starshipId + ".jpg";
            loadImage(binding.getRoot(), currentUrl,  binding.itemImage);

            binding.executePendingBindings();

        }

        public void navigateToStarshipDetailFragment(Starship starship, View view) {
            Bitmap bitmapImage = ((BitmapDrawable) binding.itemImage.getDrawable()).getBitmap();

            HomeFragmentDirections.ActionHomeFragmentToStarshipDetailFragment direction =
                HomeFragmentDirections.actionHomeFragmentToStarshipDetailFragment(starship, bitmapImage);

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
        void onItemClick(Starship starship);
    }

}

