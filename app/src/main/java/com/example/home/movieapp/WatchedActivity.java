package com.example.home.movieapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.toolbox.StringRequest;
import com.example.home.movieapp.adapter.MovieAdapter;
import com.example.home.movieapp.helper.DataBaseHelperMovie;
import com.example.home.movieapp.model.Movie;
import com.example.home.movieapp.model.User;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class WatchedActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    ListView lvMovie;
    List<Movie> mMovieList;
    Movie movie;
    DataBaseHelperMovie dbM;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watched);
        BottomNavigationView bottomNavBar = (BottomNavigationView) findViewById(R.id.bottomNavBar);
        bottomNavBar.setOnNavigationItemSelectedListener(this);

        lvMovie=(ListView) findViewById(R.id.listview_movie);
        mMovieList = new ArrayList<>();

        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);

        String userObject= sharedPreferences.getString("user",null);
        Gson gson = new Gson();
        User user = gson.fromJson(userObject, User.class);
        dbM=new DataBaseHelperMovie(this);
        mMovieList=dbM.getAll(Integer.valueOf(user.getId()));
        MovieAdapter movieAdapter = new MovieAdapter(this, mMovieList);
        lvMovie.setAdapter(movieAdapter);
        movieAdapter.notifyDataSetChanged();

        lvMovie.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Movie movieItem=(Movie) adapterView.getAdapter().getItem(i);
                Gson gson = new Gson();
                String json = gson.toJson(movieItem);
                Intent intent = new Intent(WatchedActivity.this, InfMovieActivity.class);
                intent.putExtra("movieItem", json);
                startActivity(intent);

            }
        }
        );


    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        if(id==R.id.addFilmWatched)
        {
            startActivity(new Intent(this, AddNewWatchedMovieActivity.class));
        }
        return false;
    }



}
