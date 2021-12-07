package com.example.starwars.models.specie;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SpecieResponse {

    @SerializedName("count")
    @Expose
    private int count;
    @SerializedName("next")
    @Expose
    private String next;
    @SerializedName("previous")
    @Expose
    private String previous;
    @SerializedName("results")
    @Expose
    private List<Specie> results;

    public int getCount() {
        return count;
    }

    public String getNext() {
        return next;
    }

    public String getPrevious() {
        return previous;
    }

    public List<Specie> getResults() {
        return results;
    }

}
