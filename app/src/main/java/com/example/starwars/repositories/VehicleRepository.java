package com.example.starwars.repositories;

import androidx.lifecycle.MutableLiveData;

import com.example.starwars.models.vehicle.*;
import com.example.starwars.network.RetrofitClient;
import com.example.starwars.services.VehicleService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VehicleRepository {

    private final String TAG = "VehicleRepository";

    private MutableLiveData<List<Vehicle>> vehicles;
    private VehicleService service;

    Integer nextPage = 2;

    public VehicleRepository(MutableLiveData<List<Vehicle>> vehicles) {
        this.vehicles = vehicles;
        this.service = RetrofitClient.getInstance().create(VehicleService.class);
    }

    public MutableLiveData<List<Vehicle>> getDataFromAPI(int page) {

        Call<VehicleResponse> call = service.getVehicles(page);

        call.enqueue(new Callback<VehicleResponse>() {
            @Override
            public void onResponse(Call<VehicleResponse> call, Response<VehicleResponse> response) {
                if(response.isSuccessful()) {
                    if(response.body().getNext() == null) {
                        nextPage = null;
                    }
                    vehicles.postValue(response.body().getResults());
                } else {
                    vehicles.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<VehicleResponse> call, Throwable t) {
                vehicles.postValue(null);
            }
        });

        return vehicles;

    }

}
