package com.example.starwars.services;

import com.example.starwars.models.starship.*;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface StarshipService {

    @GET("starships")
    Call<StarshipResponse> getStarships(
        @Query("page") int page
    );

}
