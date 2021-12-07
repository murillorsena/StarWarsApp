package com.example.starwars.services;

import com.example.starwars.models.planet.*;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PlanetService {

    @GET("planets")
    Call<PlanetResponse> getPlanets(
        @Query("page") int page
    );

}
