package com.example.starwars.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.starwars.models.vehicle.Vehicle;
import com.example.starwars.repositories.VehicleRepository;

import java.util.List;

public class VehicleViewModel extends ViewModel {

    private final String TAG = "VehicleViewModel";

    private MutableLiveData<List<Vehicle>> vehicles;
    private VehicleRepository repository;

    public VehicleViewModel() {
        this.vehicles = new MutableLiveData<>();
        this.repository = new VehicleRepository(vehicles);
    }

    public LiveData<List<Vehicle>> getVehicles() {
        return repository.getDataFromAPI(1);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        this.vehicles = null;
    }

}
