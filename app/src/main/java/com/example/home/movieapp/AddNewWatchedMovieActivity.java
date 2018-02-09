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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.home.movieapp.adapter.MovieAdapter;
import com.example.home.movieapp.helper.DataBaseHelperMovie;
import com.example.home.movieapp.model.Movie;
import com.example.home.movieapp.model.User;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AddNewWatchedMovieActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{


    public List<JSONObject> list;
    SQLiteDatabase db;
    DataBaseHelperMovie dataBaseHelperMovie;
    public String url;
    private ListView lvMovie;
    private MovieAdapter movieAdapter;
    private List<Movie> movieList;
    public StringRequest stringRequest;
    public boolean taskDone=false;
    public EditText addNewMovie;
    Movie movie;
    //public Movie movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_film_watched);
        addNewMovie = (EditText) findViewById(R.id.searchMovie);

        BottomNavigationView bottomNavBarBack = (BottomNavigationView) findViewById(R.id.bottomNavBarBack);
        bottomNavBarBack.setOnNavigationItemSelectedListener(this);
        //iz ovog aktivija zovemo api

        Button btnSearchWatchedMovie = (Button) findViewById(R.id.btnSearchMovie);

        btnSearchWatchedMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("button clicked");
                //ovo je red aktivnosti, i radi aktivnost po aktivnost
                final RequestQueue requestQueue = Volley.newRequestQueue(AddNewWatchedMovieActivity.this);
                url="http://www.omdbapi.com/?t="+addNewMovie.getText().toString().replace(" ","+")+"&apikey=32846af3";
                stringRequest= new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                    //ako dodbijem odgovor dobicu string ako ne, ulazi u error i vraca gresku
                    @Override
                    public void onResponse(String response) {
                        System.out.println("prosao");
                        //api sve vrati u stringu
                        //tako da moram da ga konvertujem u json objekat kako bih mogla da radim sa tim
                        //konvertujem ga preko gson objekta
                        try{
                            //pomocu sharedPreferences uzimamo usera iz local storage i koristimo njegov id
                            //kako bismo povezali podatke o filmu sa konkretnim userom
                            SharedPreferences sharedPreferences= getSharedPreferences("shared preferences", MODE_PRIVATE);
                            String userObject=sharedPreferences.getString("user",null);
                            Gson gson = new Gson();

                            User user = gson.fromJson(userObject, User.class);


                            JSONObject jsonObject = new JSONObject(response);
                            Movie movie = new Movie();
                            movie.setUserId(new Integer(user.getId()));
                            movie.setActors(jsonObject.getString("Actors"));
                            movie.setYear(jsonObject.getString("Year"));
                            movie.setAwards(jsonObject.getString("Awards"));
                            movie.setDirector(jsonObject.getString("Director"));
                            movie.setGenre(jsonObject.getString("Genre"));
                            movie.setImdbRating(jsonObject.getDouble("imdbRating"));
                            movie.setPoster(jsonObject.getString("Poster"));
                            movie.setTitle(jsonObject.getString("Title"));
                            movie.setWatched(true);
                            System.out.println(movie);

                            Intent intent = new Intent(AddNewWatchedMovieActivity.this, PutRatesAndCommentsActivity.class);
                            Gson object = new Gson();
                            requestQueue.stop();
                            //gson ima metodu kojom ceo objekat mogu da pretvorim u json
                            String movieString = object.toJson(movie);
                            intent.putExtra("movie", movieString);
                            startActivity(intent);




                        } catch (JSONException e) {
                            System.out.println("nije prosao");
                            e.printStackTrace();
                            Toast.makeText(AddNewWatchedMovieActivity.this, "Movie not found", Toast.LENGTH_SHORT).show();

                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Toast.makeText(AddNewWatchedMovieActivity.this, "Movie not found", Toast.LENGTH_SHORT).show();
                        requestQueue.stop();

                    }
                });
                //ovo dodam u tu listu aktivnosti
                requestQueue.add(stringRequest);
                //to je kad izvrsi aktivnosti
                requestQueue.addRequestFinishedListener(new RequestQueue.RequestFinishedListener<Movie>() {
                    @Override
                    public void onRequestFinished(Request<Movie> request)
                    {
                        System.out.println("zavrsio");

                    }
                });
            }
        });

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
