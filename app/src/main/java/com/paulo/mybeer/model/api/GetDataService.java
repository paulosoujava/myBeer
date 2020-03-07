package com.paulo.mybeer.model.api;

import com.paulo.mybeer.model.data.entity.Beer;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GetDataService {


    @GET("/v2/beers")
    Call<List<Beer>> getAllBeer();
    @GET("/v2/beers?")
    Call<List<Beer>> getPunkBeerList(@Query("page") int page, @Query("per_page") int per_page);
}
