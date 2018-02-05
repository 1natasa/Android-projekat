package com.example.home.movieapp.api;

import android.content.Context;

import com.android.volley.toolbox.StringRequest;
import com.example.home.movieapp.model.Movie;

/**
 * Created by Home on 2.2.2018..
 */

public class MovieApi {
    public Context context;
    public String url;
    public StringRequest stringRequest;
    public Movie movie;
    public MovieApi(Context context)
    {
        this.context=context;
    }
    //za svaki poziv prema apiju moracu da znam odakle ga zovem

}


