package com.paulo.mybeer.model.api;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.paulo.mybeer.model.data.entity.Beer;
import com.paulo.mybeer.utils.Utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Api {

    private static Retrofit retrofit;
    private static final String BASE_URL = "https://api.punkapi.com/";

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(new GsonBuilder()
                            .setLenient()
                            .create()))

                    .build();
        }
        return retrofit;
    }

    public static   List<Beer> test(){
        List<Beer> beers = new ArrayList<>();
        Beer b = new Beer();
        b.setId(1F);
        b.setName("Punk IPA 1");
        b.setImage_url("https://images.punkapi.com/v2/192.png");
        b.setTag_line("Post Modern Classic. Spiky. Tropical. Hoppy.");
        b.setDescription("Our flagship beer that kick started the craft beer revolution. This is James and Martin's original take on an American IPA, subverted with punchy New Zealand hops. Layered with new world hops to create an all-out riot of grapefruit, pineapple and lychee before a spiky, mouth-puckering bitter finish.");
        b.setAttenuation_level(2F);

        Beer b2 = new Beer();
        b2.setId(2F);
        b2.setName("Punk IPA 2");
        b2.setImage_url("https://images.punkapi.com/v2/192.png");
        b2.setTag_line("Post Modern Classic. Spiky. Tropical. Hoppy.");
        b2.setDescription("Our flagship beer that kick started the craft beer revolution. This is James and Martin's original take on an American IPA, subverted with punchy New Zealand hops. Layered with new world hops to create an all-out riot of grapefruit, pineapple and lychee before a spiky, mouth-puckering bitter finish.");
        b2.setAttenuation_level(2F);

        Beer b3 = new Beer();
        b3.setId(3F);
        b3.setName("Punk IPA 3");
        b3.setImage_url("https://images.punkapi.com/v2/192.png");
        b3.setTag_line("Post Modern Classic. Spiky. Tropical. Hoppy.");
        b3.setDescription("Our flagship beer that kick started the craft beer revolution. This is James and Martin's original take on an American IPA, subverted with punchy New Zealand hops. Layered with new world hops to create an all-out riot of grapefruit, pineapple and lychee before a spiky, mouth-puckering bitter finish.");
        b3.setAttenuation_level(2F);
        Beer b4 = new Beer();
        b4.setId(4F);
        b4.setName("Punk IPA 4");
        b4.setImage_url("https://images.punkapi.com/v2/192.png");
        b4.setTag_line("Post Modern Classic. Spiky. Tropical. Hoppy.");
        b4.setDescription("Our flagship beer that kick started the craft beer revolution. This is James and Martin's original take on an American IPA, subverted with punchy New Zealand hops. Layered with new world hops to create an all-out riot of grapefruit, pineapple and lychee before a spiky, mouth-puckering bitter finish.");
        b4.setAttenuation_level(2F);

        Beer b5 = new Beer();
        b5.setId(5F);
        b5.setName("Punk IPA 5");
        b5.setImage_url("https://images.punkapi.com/v2/192.png");
        b5.setTag_line("Post Modern Classic. Spiky. Tropical. Hoppy.");
        b5.setDescription("Our flagship beer that kick started the craft beer revolution. This is James and Martin's original take on an American IPA, subverted with punchy New Zealand hops. Layered with new world hops to create an all-out riot of grapefruit, pineapple and lychee before a spiky, mouth-puckering bitter finish.");
        b5.setAttenuation_level(2F);
        Beer b6 = new Beer();
        b6.setId(6F);
        b6.setName("Punk IPA 6");
        b6.setImage_url("https://images.punkapi.com/v2/192.png");
        b6.setTag_line("Post Modern Classic. Spiky. Tropical. Hoppy.");
        b6.setDescription("Our flagship beer that kick started the craft beer revolution. This is James and Martin's original take on an American IPA, subverted with punchy New Zealand hops. Layered with new world hops to create an all-out riot of grapefruit, pineapple and lychee before a spiky, mouth-puckering bitter finish.");
        b6.setAttenuation_level(2F);

        beers.add( b );
        beers.add( b2 );
        beers.add( b3 );
        beers.add( b4 );
        beers.add( b5 );
        beers.add( b6 );

        return  beers;

    }

}
