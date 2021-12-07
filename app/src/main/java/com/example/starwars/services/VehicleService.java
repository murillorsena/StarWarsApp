package com.example.starwars.services;

import com.example.starwars.models.vehicle.*;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface VehicleService {

    @GET("vehicles")
    Call<VehicleResponse> getVehicles(
        @Query("page") int page
    );

}
