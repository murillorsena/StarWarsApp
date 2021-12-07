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
import com.example.starwars.databinding.ItemFilmBinding;
import com.example.starwars.fragments.HomeFragmentDirections;
import com.example.starwars.models.film.Film;
import com.example.starwars.models.planet.Planet;

import java.util.ArrayList;
import java.util.List;

public class FilmAdapter extends RecyclerView.Adapter<FilmAdapter.ViewHolder> {

    private final String TAG = "FilmAdapter";
    private final String IMG_URL = "https://starwars-visualguide.com/assets/img/films/";
    private final String IMG_ERROR_URL = "https://starwars-visualguide.com/assets/img/placeholder.jpg";

    private Context context;
    private List<Film> films;

    public FilmAdapter(Context context) {
        this.context = context;
        this.films = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemFilmBinding itemBinding = ItemFilmBinding.inflate(layoutInflater, parent, false);

        return new ViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Film film = films.get(position);
        Log.d(TAG, "FILM -> " + film);
        holder.bind(film);
    }

    @Override
    public int getItemCount() {
        return films == null ? 0 : films.size();
    }

    public void addFilms(List<Film> films) {
        this.films.clear();
        this.films.addAll(films);
        this.notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final ItemFilmBinding binding;

        public ViewHolder(ItemFilmBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Film film) {

            binding.setFilm(film);

            binding.setOnItemClick(new OnItemClickListener() {
                @Override
                public void onItemClick(Film film) {
                    navigateToFilmDetailFragment(film, binding.getRoot());
                }
            });

            String filmId = film.getUrl().split("/")[5];
            String currentUrl = IMG_URL + filmId + ".jpg";
            loadImage(binding.getRoot(), currentUrl, binding.itemImage);

            binding.executePendingBindings();

        }

        public void navigateToFilmDetailFragment(Film film, View view) {
            Bitmap bitmapImage = ((BitmapDrawable) binding.itemImage.getDrawable()).getBitmap();

            HomeFragmentDirections.ActionHomeFragmentToFilmDetailFragment direction =
                HomeFragmentDirections.actionHomeFragmentToFilmDetailFragment(film, bitmapImage);

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
        void onItemClick(Film film);
    }

}
