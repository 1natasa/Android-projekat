package com.example.home.movieapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

public class ToWatchActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_watch);
        BottomNavigationView bottomNavBarToWatch = (BottomNavigationView) findViewById(R.id.bottomNavBarToWatch);
        bottomNavBarToWatch.setOnNavigationItemSelectedListener(this);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        if(id==R.id.addFilmTowatch)
        {
            startActivity(new Intent(this, AddNewToWatchMovieActivity.class));
        }
        return false;
    }
}
