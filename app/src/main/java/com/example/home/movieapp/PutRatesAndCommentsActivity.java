package com.example.home.movieapp;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.home.movieapp.helper.DataBaseHelperMovie;
import com.example.home.movieapp.model.Movie;
import com.google.gson.Gson;

import org.json.JSONException;

import java.lang.reflect.Type;

public class PutRatesAndCommentsActivity extends AppCompatActivity {
    DataBaseHelperMovie helperMovie;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_put_rates_and_comments);
        final EditText etRateMovie= (EditText) findViewById(R.id.rateMovie);
        final EditText etCommentMovie = (EditText) findViewById(R.id.etCommentMovie);
        Intent intent = getIntent();
        String movieString = intent.getExtras().getString("movie");
        Gson object = new Gson();
        helperMovie = new DataBaseHelperMovie(this);

        final Movie movieObject = object.fromJson(movieString,Movie.class);

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
                    Toast.makeText(PutRatesAndCommentsActivity.this, "Film je sacuvan", Toast.LENGTH_SHORT).show();
                    //startActivity(new Intent(this, WatchedActivity.class));

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        });
        //Toast.makeText(PutRatesAndCommentsActivity.this, "Film je sacuvan", Toast.LENGTH_SHORT).show();
        //startActivity(new Intent(this, WatchedActivity.class));

    }
}
