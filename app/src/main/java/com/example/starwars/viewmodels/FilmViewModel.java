package com.example.starwars.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.starwars.models.film.Film;
import com.example.starwars.repositories.FilmRepository;

import java.util.List;

public class FilmViewModel extends ViewModel {

    private final String TAG = "FilmViewModel";

    private MutableLiveData<List<Film>> films;
    private FilmRepository repository;

    public FilmViewModel() {
        this.films = new MutableLiveData<>();
        this.repository = new FilmRepository(films);
    }

    public LiveData<List<Film>> getFilms() {
        return repository.getDataFromAPI(1);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        this.films = null;
    }

}
