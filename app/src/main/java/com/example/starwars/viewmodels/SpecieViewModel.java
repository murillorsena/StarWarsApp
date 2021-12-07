package com.example.starwars.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.starwars.models.specie.Specie;
import com.example.starwars.repositories.SpecieRepository;

import java.util.List;

public class SpecieViewModel extends ViewModel {

    private final String TAG = "SpecieViewModel";

    private MutableLiveData<List<Specie>> species;
    private SpecieRepository repository;

    public SpecieViewModel() {
        this.species = new MutableLiveData<>();
        this.repository = new SpecieRepository(species);
    }

    public LiveData<List<Specie>> getSpecies() {
        return repository.getDataFromAPI(1);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        this.species = null;
    }

}
