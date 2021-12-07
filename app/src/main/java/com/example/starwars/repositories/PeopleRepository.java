package com.example.starwars.repositories;

import androidx.lifecycle.MutableLiveData;

import com.example.starwars.models.people.*;
import com.example.starwars.network.RetrofitClient;
import com.example.starwars.services.PeopleService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PeopleRepository {

    private final String TAG = "PeopleRepository";

    private MutableLiveData<List<People>> peoples;
    private PeopleService service;

    Integer nextPage = 2;

    public PeopleRepository(MutableLiveData<List<People>> peoples) {
        this.peoples = peoples;
        this.service = RetrofitClient.getInstance().create(PeopleService.class);
    }

    public MutableLiveData<List<People>> getDataFromAPI(int page) {

        Call<PeopleResponse> call = service.getPeoples(page);
        
        call.enqueue(new Callback<PeopleResponse>() {
            @Override
            public void onResponse(Call<PeopleResponse> call, Response<PeopleResponse> response) {
                if(response.isSuccessful()) {
                    if(response.body().getNext() == null) {
                        nextPage = null;
                    }
                    peoples.postValue(response.body().getResults());
                } else {
                    peoples.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<PeopleResponse> call, Throwable t) {
                peoples.postValue(null);
            }
        });

        return peoples;

    }

}
