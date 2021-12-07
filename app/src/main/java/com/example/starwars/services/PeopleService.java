package com.example.starwars.services;

import com.example.starwars.models.people.*;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PeopleService {

    @GET("people")
    Call<PeopleResponse> getPeoples(
        @Query("page") int page
    );

}
