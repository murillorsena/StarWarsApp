package com.example.starwars.network;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

import retrofit2.Retrofit;
import retrofit2.GsonConverterFactory;

public class RetrofitClient {

    private static Retrofit instance;
    private static final String BASE_URL = "https://swapi.dev/api/";

    private RetrofitClient() {}

    public static Retrofit getInstance() {

        if(instance == null) {

            HttpLoggingInterceptor logger = new HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BASIC);

            OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(logger)
                .build();

            instance = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        }

        return instance;

    }

}
