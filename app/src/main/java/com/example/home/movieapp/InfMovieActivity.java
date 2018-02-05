package com.example.home.movieapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.example.home.movieapp.model.Movie;
import com.google.gson.Gson;

public class InfMovieActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inf_movie);

        String movie = getIntent().getExtras().getString("movieItem");
        Gson gson = new Gson();
        Movie movieObject= gson.fromJson(movie, Movie.class);

        EditText etTitle = (EditText) findViewById(R.id.etName);
        etTitle.setText(movieObject.getTitle());

        System.out.println(movie);
    }
}
