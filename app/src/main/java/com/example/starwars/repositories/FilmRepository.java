package com.example.starwars.repositories;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.starwars.models.film.*;
import com.example.starwars.network.RetrofitClient;
import com.example.starwars.services.FilmService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FilmRepository {

    private final String TAG = "FilmRepository";

    private MutableLiveData<List<Film>> films;
    private FilmService service;

    Integer nextPage = 2;

    public FilmRepository(MutableLiveData<List<Film>> films) {
        this.films = films;
        this.service = RetrofitClient.getInstance().create(FilmService.class);
    }

    public MutableLiveData<List<Film>> getDataFromAPI(int page) {

        Call<FilmResponse> call = service.getFilms(page);

        call.enqueue(new Callback<FilmResponse>() {
            @Override
            public void onResponse(Call<FilmResponse> call, Response<FilmResponse> response) {
                if(response.isSuccessful()) {
                    if(response.body().getNext() == null) {
                        nextPage = null;
                    }
                    films.postValue(response.body().getResults());
                } else {
                    films.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<FilmResponse> call, Throwable t) {
                films.postValue(null);
            }
        });

        return films;

    }

}
