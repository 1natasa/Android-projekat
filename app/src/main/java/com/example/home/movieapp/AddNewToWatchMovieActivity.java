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
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.home.movieapp.helper.DataBaseHelperMovie;
import com.example.home.movieapp.model.Movie;
import com.example.home.movieapp.model.User;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

public class AddNewToWatchMovieActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    public EditText addNewMovieToWatch;
    public String url;
    public StringRequest stringRequest;
    SQLiteDatabase db;
    DataBaseHelperMovie dataBaseHelperMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_to_watch_movie);

        addNewMovieToWatch=(EditText) findViewById(R.id.etfilmToWatch);
        BottomNavigationView bottomNavBarBack = (BottomNavigationView) findViewById(R.id.bottomNavBarBack);
        bottomNavBarBack.setOnNavigationItemSelectedListener(this);
        dataBaseHelperMovie=new DataBaseHelperMovie(this);
        Button btnSaveMovieToWatch = (Button) findViewById(R.id.btnSaveFilmToWatch);
        btnSaveMovieToWatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("button clicked");
                //ovo je red aktivnosti, i radi aktivnost po aktivnost
                final RequestQueue requestQueue = Volley.newRequestQueue(AddNewToWatchMovieActivity.this);
                url="http://www.omdbapi.com/?t="+addNewMovieToWatch.getText().toString().replace(" ","+")+"&apikey=32846af3";
                stringRequest= new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                    //ako dodbijem odg dobicu string ako ne, ulazi u error i vraca neku gresku..
                    @Override
                    public void onResponse(String response) {
                        System.out.println("prosao");
                        //pravimo json u koji konvertujemo string koji nam vraca api, posto sve vrati u stringu
                        // moramo da ga konvertujemo u json objekat kako bi mogli da radimo sa tim
                        try{
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
                            movie.setWatched(false);
                            movie.setMyRate("");
                            movie.setMyComment("");

                            System.out.println(movie);
                            try {
                                dataBaseHelperMovie.insertMovie(movie);
                                Toast.makeText(AddNewToWatchMovieActivity.this, "Movie is saved", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(AddNewToWatchMovieActivity.this, ToWatchActivity.class));

                            } catch (JSONException e) {

                                e.printStackTrace();
                            }

                           // Intent intent = new Intent(AddNewWatchedMovieActivity.this, PutRatesAndCommentsActivity.class);
                           // Gson object = new Gson();
                            requestQueue.stop();

                            //String movieString = object.toJson(movie);
                            //intent.putExtra("movie", movieString);
                            //movieList.add(movie);
                          /*  movieList.add(new Movie(movie.getId(),movie.getTitle(), movie.getImdbRating() ));
                            movieAdapter.notifyDataSetChanged();
                            //DOVDE
                            */
                            //startActivity(intent);




                        } catch (JSONException e) {
                            System.out.println("nije prosao");
                            Toast.makeText(AddNewToWatchMovieActivity.this, "Movie not found", Toast.LENGTH_SHORT).show();
                            e.printStackTrace();

                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        requestQueue.stop();

                    }
                });
                //ovo dodamo u tu listu aktivnosti
                requestQueue.add(stringRequest);
                //to je kad izvrsi aktivnosti
                requestQueue.addRequestFinishedListener(new RequestQueue.RequestFinishedListener<Movie>() {
                    @Override
                    public void onRequestFinished(Request<Movie> request)
                    {
                        System.out.println("zavrsio");
                        /*if (movieList.size() != 0)
                        {
                            for (Movie movie : movieList)
                            {
                                try {
                                    //List<Movie> list1 = dataBaseHelperMovie.insertMovie(movie);
                                    Gson gson = new Gson();
                                    //String json = gson.toJson(list1);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }*/
                        //dovde
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
            startActivity(new Intent(this, ToWatchActivity.class));
        }
        return false;
    }
}
