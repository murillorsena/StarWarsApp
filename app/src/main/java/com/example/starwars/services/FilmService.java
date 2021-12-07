package com.example.starwars.services;

import com.example.starwars.models.film.*;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface FilmService {

    @GET("films")
    Call<FilmResponse> getFilms(
        @Query("page") int page
    );

}
