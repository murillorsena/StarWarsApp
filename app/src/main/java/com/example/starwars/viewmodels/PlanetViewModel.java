package com.example.starwars.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.starwars.models.planet.Planet;
import com.example.starwars.repositories.PlanetRepository;

import java.util.List;

public class PlanetViewModel extends ViewModel {

    private final String TAG = "PlanetViewModel";

    private MutableLiveData<List<Planet>> planets;
    private PlanetRepository repository;

    public PlanetViewModel() {
        this.planets = new MutableLiveData<>();
        this.repository = new PlanetRepository(planets);
    }

    public LiveData<List<Planet>> getPlanets() {
        return repository.getDataFromAPI(1);
    }

    public LiveData<List<Planet>> getMorePlanets() {
        return repository.getDataFromAPI(1);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        this.planets = null;
    }

}
