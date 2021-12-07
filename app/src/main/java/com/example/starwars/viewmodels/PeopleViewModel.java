package com.example.starwars.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.starwars.models.people.People;
import com.example.starwars.repositories.PeopleRepository;

import java.util.List;

public class PeopleViewModel extends ViewModel {

    private final String TAG = "PeopleViewModel";

    private MutableLiveData<List<People>> peoples;
    private PeopleRepository repository;

    public PeopleViewModel() {
        this.peoples = new MutableLiveData<>();
        this.repository = new PeopleRepository(peoples);
    }

    public LiveData<List<People>> getPeoples() {
        return repository.getDataFromAPI(1);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        this.peoples = null;
    }

}
