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
import com.example.starwars.databinding.ItemPeopleBinding;
import com.example.starwars.fragments.HomeFragmentDirections;
import com.example.starwars.models.people.People;

import java.util.ArrayList;
import java.util.List;

public class PeopleAdapter extends RecyclerView.Adapter<PeopleAdapter.ViewHolder> {

    private final String TAG = "PeopleAdapter";
    private final String IMG_URL = "https://starwars-visualguide.com/assets/img/characters/";
    private final String IMG_ERROR_URL = "https://starwars-visualguide.com/assets/img/placeholder.jpg";

    private Context context;
    private List<People> peoples;

    private PeopleAdapter.OnItemClickListener onItemClickListener;

    public PeopleAdapter(Context context) {
        this.context = context;
        this.peoples = new ArrayList<>();;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemPeopleBinding itemBinding = ItemPeopleBinding.inflate(layoutInflater, parent, false);

        return new ViewHolder(itemBinding);
    }

    @NonNull
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        People people = peoples.get(position);
        holder.bind(people);
    }

    @Override
    public int getItemCount() {
        return peoples == null ? 0 : peoples.size();
    }

    public void addPeoples(List<People> peoples) {
        this.peoples.clear();
        this.peoples.addAll(peoples);
        this.notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final ItemPeopleBinding binding;

        public ViewHolder(ItemPeopleBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(People people) {

            binding.setPeople(people);
            binding.setOnItemClick(new OnItemClickListener() {
                @Override
                public void onItemClick(People people) {
                    Bitmap bitmap = ((BitmapDrawable) binding.itemImage.getDrawable()).getBitmap();

                    HomeFragmentDirections.ActionHomeFragmentToPeopleDetailFragment directions =
                        HomeFragmentDirections.actionHomeFragmentToPeopleDetailFragment(people, bitmap);

                    Navigation.findNavController(binding.getRoot()).navigate(directions);
                }
            });

            String peopleId = people.getUrl().split("/")[5];
            String currentUrl = IMG_URL + peopleId + ".jpg";

            loadImage(binding.getRoot(), currentUrl, binding.itemImage);

            binding.executePendingBindings();

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
        void onItemClick(People people);
    }

}