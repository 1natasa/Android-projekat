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
import android.widget.TextView;
import android.widget.Toast;

import com.example.home.movieapp.helper.DataBaseHelperMovie;
import com.example.home.movieapp.model.Movie;
import com.google.gson.Gson;

import org.json.JSONException;

public class InfMovieActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    String movie;
    DataBaseHelperMovie db;
    Movie movieObject;
    Gson gson;
    EditText tvRates;
    EditText tvComment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inf_movie);

        BottomNavigationView bottomNavBar = (BottomNavigationView) findViewById(R.id.bottomNavBarImbdRates);
        bottomNavBar.setOnNavigationItemSelectedListener(this);
        db=new DataBaseHelperMovie(this);
        movie = getIntent().getExtras().getString("movieItem");
        gson = new Gson();
        movieObject = gson.fromJson(movie, Movie.class);

        TextView tvTitle = (TextView) findViewById(R.id.etName);
        tvTitle.setText(movieObject.getTitle());

        tvRates = (EditText) findViewById(R.id.etRates);
        tvRates.setText(movieObject.getMyRate());

        tvComment = (EditText) findViewById(R.id.etComment);
        tvComment.setText(movieObject.getMyComment());
        Button btnSave = (Button) findViewById(R.id.btnSave);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    movieObject.setMyRate(tvRates.getText().toString());
                    movieObject.setMyComment(tvComment.getText().toString());
                    System.out.println(gson.toJson(movieObject));
                    Toast.makeText(InfMovieActivity.this, "Sacuvane izmene", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(InfMovieActivity.this, WatchedActivity.class));
                    db.update(movieObject);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });

        //Button imdbRates = (Button) findViewById(R.id.bottomNavBarImbdRates);


        System.out.println(movie);
    }



    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int id=item.getItemId();
        if(id==R.id.toIMBDRates)
        {
            Intent intent = new Intent(this, ImbdInfoMovieActivity.class);
            intent.putExtra("movieImbdItem",movie);

            startActivity(intent);

        } else if (id==R.id.back)
        {
            startActivity(new Intent(this, WatchedActivity.class));
        }
        return false;
    }
}
