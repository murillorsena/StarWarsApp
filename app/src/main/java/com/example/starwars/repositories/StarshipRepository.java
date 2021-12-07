package com.example.starwars.repositories;

import androidx.lifecycle.MutableLiveData;

import com.example.starwars.models.starship.*;
import com.example.starwars.network.RetrofitClient;
import com.example.starwars.services.StarshipService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StarshipRepository {

    private final String TAG = "StarshipRepository";

    private MutableLiveData<List<Starship>> starships;
    private StarshipService service;

    Integer nextPage = 2;

    public StarshipRepository(MutableLiveData<List<Starship>> starships) {
        this.starships = starships;
        this.service = RetrofitClient.getInstance().create(StarshipService.class);
    }

    public MutableLiveData<List<Starship>> getDataFromAPI(int page) {

        Call<StarshipResponse> call = service.getStarships(page);

        call.enqueue(new Callback<StarshipResponse>() {
            @Override
            public void onResponse(Call<StarshipResponse> call, Response<StarshipResponse> response) {
                if(response.isSuccessful()) {
                    if(response.body().getNext() == null) {
                        nextPage = null;
                    }
                    starships.postValue(response.body().getResults());
                } else {
                    starships.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<StarshipResponse> call, Throwable t) {
                starships.postValue(null);
            }
        });

        return starships;

    }

}
