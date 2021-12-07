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
import com.example.starwars.databinding.ItemVehicleBinding;
import com.example.starwars.fragments.HomeFragmentDirections;
import com.example.starwars.models.specie.Specie;
import com.example.starwars.models.vehicle.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class VehicleAdapter extends RecyclerView.Adapter<VehicleAdapter.ViewHolder> {

    private final String TAG = "VehicleAdapter";
    private final String IMG_URL = "https://starwars-visualguide.com/assets/img/vehicles/";
    private final String IMG_ERROR_URL = "https://starwars-visualguide.com/assets/img/placeholder.jpg";

    private Context context;
    private List<Vehicle> vehicles;

    public VehicleAdapter(Context context) {
        this.context = context;
        this.vehicles = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemVehicleBinding itemBinding = ItemVehicleBinding.inflate(layoutInflater, parent, false);

        return new ViewHolder(itemBinding);
    }

    @NonNull
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Vehicle vehicle = vehicles.get(position);
        holder.bind(vehicle);
    }

    @Override
    public int getItemCount() {
        return vehicles == null ? 0 : vehicles.size();
    }

    public void addVehicles(List<Vehicle> vehicles) {
        this.vehicles.clear();
        this.vehicles.addAll(vehicles);
        this.notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final ItemVehicleBinding binding;

        public ViewHolder(ItemVehicleBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Vehicle vehicle) {

            binding.setVehicle(vehicle);

            binding.setOnItemClick(new OnItemClickListener() {
                @Override
                public void onItemClick(Vehicle vehicle) {
                    navigateToVehicleDetailFragment(vehicle, binding.getRoot());
                }
            });

            String vehicleId = vehicle.getUrl().split("/")[5];
            String currentUrl = IMG_URL + vehicleId + ".jpg";
            loadImage(binding.getRoot(), currentUrl,  binding.itemImage);

            binding.executePendingBindings();

        }

        public void navigateToVehicleDetailFragment(Vehicle vehicle, View view) {
            Bitmap bitmapImage = ((BitmapDrawable) binding.itemImage.getDrawable()).getBitmap();

            HomeFragmentDirections.ActionHomeFragmentToVehicleDetailFragment direction =
                HomeFragmentDirections.actionHomeFragmentToVehicleDetailFragment(vehicle, bitmapImage);

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
        void onItemClick(Vehicle vehicle);
    }

}

