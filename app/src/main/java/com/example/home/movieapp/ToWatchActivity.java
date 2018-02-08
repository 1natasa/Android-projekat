package com.example.home.movieapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.home.movieapp.adapter.ToWatchMovieAdapter;
import com.example.home.movieapp.helper.DataBaseHelperMovie;
import com.example.home.movieapp.model.Movie;
import com.example.home.movieapp.model.User;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ToWatchActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener  {

    ToWatchMovieAdapter adapter ;
    ListView listView;
    List<Movie> movieList;
    Movie movie;
    DataBaseHelperMovie dbM;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_watch);
        BottomNavigationView bottomNavBarToWatch = (BottomNavigationView) findViewById(R.id.bottomNavBarToWatch);
        bottomNavBarToWatch.setOnNavigationItemSelectedListener(this);

        listView=(ListView)findViewById(R.id.listview_movie);
        movieList=new ArrayList<>();
        dbM=new DataBaseHelperMovie(this);
        SharedPreferences sharedPreferences =getSharedPreferences("shared preferences",MODE_PRIVATE);
        String userStr =sharedPreferences.getString("user",null);
        Gson gson = new Gson();
        User user = gson.fromJson(userStr, User.class);
        movieList=dbM.getAllToWatch(Integer.valueOf(user.getId()));
        System.out.println(movieList);
        listView.setFocusable(true);
        //nakon sto napravim objeka i stavim ga u listu, putem adaptera cu da popunim listu koja ce da se prikaze
        adapter=new ToWatchMovieAdapter(this,movieList);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        if(id==R.id.addFilmTowatch)
        {
            startActivity(new Intent(this, AddNewToWatchMovieActivity.class));
        } else if (id==R.id.back)
        {
            startActivity(new Intent(this, UserAreaActivity.class));
        }

        return false;
    }
}
