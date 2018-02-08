package com.example.home.movieapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.home.movieapp.model.Movie;
import com.google.gson.Gson;

public class ImbdInfoMovieActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imbd_info_movie);

        BottomNavigationView bottomNavBarBack = (BottomNavigationView) findViewById(R.id.bottomNavBarBack);
        bottomNavBarBack.setOnNavigationItemSelectedListener(this);

        String movie = getIntent().getExtras().getString("movieImbdItem");
        Gson gson = new Gson();
        Movie movieObject = gson.fromJson(movie, Movie.class);

        TextView tvYear =(TextView) findViewById(R.id.etImbdYear);
        tvYear.setText(movieObject.getYear());

        TextView tvGenre =(TextView) findViewById(R.id.etImdbGenre);
        tvGenre.setText(movieObject.getGenre());

        TextView tvActors =(TextView) findViewById(R.id.etImdbActors);
        tvActors.setText(movieObject.getActors());

        TextView tvDirector= (TextView) findViewById(R.id.etImdbDirector);
        tvDirector.setText(movieObject.getDirector());

        TextView tvAwards=(TextView) findViewById(R.id.etImdbAwards);
        tvAwards.setText(movieObject.getAwards());

        TextView tvRating=(TextView) findViewById(R.id.etImdbRatings);
        tvRating.setText(String.valueOf(movieObject.getImdbRating()));


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();

        if(id==R.id.back)
        {
            startActivity(new Intent(this, WatchedActivity.class));
        }
        return false;
    }
}
