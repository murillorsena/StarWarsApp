package com.example.starwars.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.starwars.models.starship.Starship;
import com.example.starwars.repositories.StarshipRepository;

import java.util.List;

public class StarshipViewModel extends ViewModel {

    private final String TAG = "StarshipViewModel";

    private MutableLiveData<List<Starship>> starships;
    private StarshipRepository repository;

    public StarshipViewModel() {
        this.starships = new MutableLiveData<>();
        this.repository = new StarshipRepository(starships);
    }

    public LiveData<List<Starship>> getStarships() {
        return repository.getDataFromAPI(1);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        this.starships = null;
    }

}
