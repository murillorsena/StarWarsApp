package com.example.starwars.repositories;

import androidx.lifecycle.MutableLiveData;

import com.example.starwars.models.specie.*;
import com.example.starwars.network.RetrofitClient;
import com.example.starwars.services.SpecieService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SpecieRepository {

    private final String TAG = "SpecieRepository";

    private MutableLiveData<List<Specie>> species;
    private SpecieService service;

    Integer nextPage = 2;

    public SpecieRepository(MutableLiveData<List<Specie>> species) {
        this.species = species;
        this.service = RetrofitClient.getInstance().create(SpecieService.class);
    }

    public MutableLiveData<List<Specie>> getDataFromAPI(int page) {

        Call<SpecieResponse> call = service.getSpecies(page);

        call.enqueue(new Callback<SpecieResponse>() {
            @Override
            public void onResponse(Call<SpecieResponse> call, Response<SpecieResponse> response) {
                if(response.isSuccessful()) {
                    if(response.body().getNext() == null) {
                        nextPage = null;
                    }
                    species.postValue(response.body().getResults());
                } else {
                    species.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<SpecieResponse> call, Throwable t) {
                species.postValue(null);
            }
        });

        return species;

    }

}
