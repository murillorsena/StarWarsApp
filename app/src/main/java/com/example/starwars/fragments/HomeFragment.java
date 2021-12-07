package com.example.starwars.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.starwars.R;
import com.example.starwars.adapters.PeopleAdapter;
import com.example.starwars.adapters.PlanetAdapter;
import com.example.starwars.adapters.FilmAdapter;
import com.example.starwars.adapters.SpecieAdapter;
import com.example.starwars.adapters.VehicleAdapter;
import com.example.starwars.adapters.StarshipAdapter;

import com.example.starwars.adapters.VerticalRecyclerViewAdapter;
import com.example.starwars.databinding.FragmentHomeBinding;

import com.example.starwars.viewmodels.PeopleViewModel;
import com.example.starwars.viewmodels.PlanetViewModel;
import com.example.starwars.viewmodels.FilmViewModel;
import com.example.starwars.viewmodels.SpecieViewModel;
import com.example.starwars.viewmodels.VehicleViewModel;
import com.example.starwars.viewmodels.StarshipViewModel;

public class HomeFragment extends Fragment {

    public static final String TAG = "HomeFragment";

    private VerticalRecyclerViewAdapter adapter;
    private FragmentHomeBinding binding;

    //private LinearLayoutManager layoutManager;

    public HomeFragment() {
        //this.layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        this.adapter = new VerticalRecyclerViewAdapter(this.getContext());
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        Log.d(TAG, "requireActivity -> " + requireActivity());

        if(savedInstanceState == null) {
            //binding.recyclerviewVertical.setLayoutManager(layoutManager);
            binding.recyclerviewVertical.setAdapter(adapter);
            binding.recyclerviewVertical.setHasFixedSize(true);
        }

        observerPeoples();
        observerPlanets();
        observerFilms();
        observerSpecies();
        observerVehicles();
        observerStarships();

    }

    public void observerPeoples() {

        PeopleViewModel peopleViewModel = new ViewModelProvider(requireActivity()).get(PeopleViewModel.class);

        PeopleAdapter peopleAdapter = (PeopleAdapter) adapter.getItemAtPosition(0);

        peopleViewModel.getPeoples().observe(getViewLifecycleOwner(), peoples -> {

            peopleAdapter.addPeoples(peoples);

        });

    }

    public void observerPlanets() {

        PlanetViewModel planetViewModel = new ViewModelProvider(requireActivity()).get(PlanetViewModel.class);

        PlanetAdapter planetAdapter = (PlanetAdapter) adapter.getItemAtPosition(1);

        planetViewModel.getPlanets().observe(getViewLifecycleOwner(), planets -> {

            planetAdapter.addPlanets(planets);

        });

    }

    public void observerFilms() {

        FilmViewModel filmViewModel = new ViewModelProvider(requireActivity()).get(FilmViewModel.class);

        FilmAdapter filmAdapter = (FilmAdapter) adapter.getItemAtPosition(2);

        filmViewModel.getFilms().observe(getViewLifecycleOwner(), films -> {

            Log.d(TAG, "films -> " + films);
            filmAdapter.addFilms(films);

        });

    }

    public void observerSpecies() {

        SpecieViewModel specieViewModel = new ViewModelProvider(requireActivity()).get(SpecieViewModel.class);

        SpecieAdapter specieAdapter = (SpecieAdapter) adapter.getItemAtPosition(3);

        specieViewModel.getSpecies().observe(getViewLifecycleOwner(), species -> {

            specieAdapter.addSpecies(species);

        });

    }

    public void observerVehicles() {

        VehicleViewModel vehicleViewModel = new ViewModelProvider(requireActivity()).get(VehicleViewModel.class);

        VehicleAdapter vehicleAdapter = (VehicleAdapter) adapter.getItemAtPosition(4);

        vehicleViewModel.getVehicles().observe(getViewLifecycleOwner(), vehicles -> {

            vehicleAdapter.addVehicles(vehicles);

        });

    }

    public void observerStarships() {

        StarshipViewModel starshipViewModel = new ViewModelProvider(requireActivity()).get(StarshipViewModel.class);

        StarshipAdapter starshipAdapter = (StarshipAdapter) adapter.getItemAtPosition(5);

        starshipViewModel.getStarships().observe(getViewLifecycleOwner(), vehicles -> {

            starshipAdapter.addStarships(vehicles);

        });

    }

}