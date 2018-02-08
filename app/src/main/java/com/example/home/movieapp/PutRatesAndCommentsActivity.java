package com.example.home.movieapp;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.home.movieapp.helper.DataBaseHelperMovie;
import com.example.home.movieapp.model.Movie;
import com.google.gson.Gson;

import org.json.JSONException;

import java.lang.reflect.Type;

public class PutRatesAndCommentsActivity extends AppCompatActivity  implements BottomNavigationView.OnNavigationItemSelectedListener{
    DataBaseHelperMovie helperMovie;
    Movie movieObject;
    EditText etRateMovie;
    EditText etCommentMovie;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_put_rates_and_comments);
          etRateMovie= (EditText) findViewById(R.id.rateMovie);
          etCommentMovie = (EditText) findViewById(R.id.etCommentMovie);
        BottomNavigationView bottomNavBarBack = (BottomNavigationView) findViewById(R.id.bottomNavBarBack);
        bottomNavBarBack.setOnNavigationItemSelectedListener(this);

        Intent intent = getIntent();
        System.out.println(intent.getExtras().getString("movieWatched") !=null);
        System.out.println(intent.getExtras().getString("movie") !=null);
        if(intent.getExtras().getString("movie")!=null)
        {
            String movieString = intent.getExtras().getString("movie");
            Gson object = new Gson();
            helperMovie = new DataBaseHelperMovie(this);

            movieObject = object.fromJson(movieString,Movie.class);

            Button btnSaveRates = (Button) findViewById(R.id.btnSaveRates);
            btnSaveRates.setOnClickListener(new View.OnClickListener(

            ) {
                @Override
                public void onClick(View v) {
                    //movieObject.setId(1);
                    movieObject.setMyComment(etCommentMovie.getText().toString());
                    movieObject.setMyRate(etRateMovie.getText().toString());
                    try {
                        helperMovie.insertMovie(movieObject);
                        Toast.makeText(PutRatesAndCommentsActivity.this, "Movie is saved", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(PutRatesAndCommentsActivity.this, WatchedActivity.class));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }
            });
        } else if(intent.getExtras().getString("movieWatched") !=null)
        {
            String movieString = intent.getExtras().getString("movieWatched");
            Gson object = new Gson();
            helperMovie = new DataBaseHelperMovie(this);

            movieObject = object.fromJson(movieString,Movie.class);

            Button btnSaveRates = (Button) findViewById(R.id.btnSaveRates);
            btnSaveRates.setOnClickListener(new View.OnClickListener(

            ) {
                @Override
                public void onClick(View v) {
                    //movieObject.setId(1);
                    movieObject.setMyComment(etCommentMovie.getText().toString());
                    movieObject.setMyRate(etRateMovie.getText().toString());
                    movieObject.setWatched(true);
                    try {
                        helperMovie.update(movieObject);
                        Toast.makeText(PutRatesAndCommentsActivity.this, "Movie is saved", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(PutRatesAndCommentsActivity.this, WatchedActivity.class));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }
            });
        }


        //Toast.makeText(PutRatesAndCommentsActivity.this, "Film je sacuvan", Toast.LENGTH_SHORT).show();
        //startActivity(new Intent(this, WatchedActivity.class));

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int id=item.getItemId();
        if(id==R.id.back)
        {
            startActivity(new Intent(this, AddNewWatchedMovieActivity.class));
        }
        return false;
    }
}