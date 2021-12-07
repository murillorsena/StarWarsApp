package com.example.starwars.repositories;

import androidx.lifecycle.MutableLiveData;

import com.example.starwars.models.planet.*;
import com.example.starwars.network.RetrofitClient;
import com.example.starwars.services.PlanetService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlanetRepository {

    private final String TAG = "PlanetRepository";

    private MutableLiveData<List<Planet>> planets;
    private PlanetService service;

    Integer nextPage = 2;

    public PlanetRepository(MutableLiveData<List<Planet>> planets) {
        this.planets = planets;
        this.service = RetrofitClient.getInstance().create(PlanetService.class);
    }

    public MutableLiveData<List<Planet>> getDataFromAPI(int page) {

        Call<PlanetResponse> call = service.getPlanets(page);

        call.enqueue(new Callback<PlanetResponse>() {
            @Override
            public void onResponse(Call<PlanetResponse> call, Response<PlanetResponse> response) {
                if(response.isSuccessful()) {
                    if(response.body().getNext() == null) {
                        nextPage = null;
                    }
                    planets.postValue(response.body().getResults());
                } else {
                    planets.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<PlanetResponse> call, Throwable t) {
                planets.postValue(null);
            }
        });

        return planets;

    }

}
