package com.example.starwars.services;

import com.example.starwars.models.specie.*;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SpecieService {

    @GET("species")
    Call<SpecieResponse> getSpecies(
        @Query("page") int page
    );

}
