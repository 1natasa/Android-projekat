package com.example.home.movieapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.home.movieapp.PutRatesAndCommentsActivity;
import com.example.home.movieapp.R;
import com.example.home.movieapp.helper.DataBaseHelperMovie;
import com.example.home.movieapp.model.Movie;
import com.google.gson.Gson;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

import static android.support.v4.content.ContextCompat.startActivity;

/**
 * Created by Home on 7.2.2018..
 */

public class ToWatchMovieAdapter extends BaseAdapter {

    private Context mContext;
    private List<Movie> movieList;
    DataBaseHelperMovie db;

    public ToWatchMovieAdapter(Context mContext, List<Movie> movieList) {
        this.mContext = mContext;
        this.movieList = movieList;
    }

    @Override
    public int getCount() {
        return movieList.size();
    }

    @Override
    public Object getItem(int position) {
        return  movieList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View view = View.inflate(mContext, R.layout.movie_item_to_watch, null);
        TextView tvTitle =(TextView) view.findViewById(R.id.tvMovieToWatchTitle);
        TextView tvRate =(TextView) view.findViewById(R.id.tvToMovieWatchRate);

        tvTitle.setText(movieList.get(position).getTitle());
        tvRate.setText(String.valueOf(movieList.get(position).getImdbRating()));

        ImageView imageView =(ImageView) view.findViewById(R.id.movie_image);
        ImageButton btnWatched = (ImageButton) view.findViewById(R.id.imageButtonWatched);

        db=new DataBaseHelperMovie(mContext);

        btnWatched.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Gson gson = new Gson();

                Intent intent = new Intent(mContext, PutRatesAndCommentsActivity.class);
                intent.putExtra("movieWatched",gson.toJson(movieList.get(position)) );
                startActivity(mContext,intent,null);

            }
        });

        if(!movieList.get(position).getPoster().equals("N/A"))
        {
            Picasso.with(mContext).load(movieList.get(position).getPoster()).into(imageView, new Callback() {
                @Override
                public void onSuccess() {

                }

                @Override
                public void onError() {

                }
            });
        } else
        {
            Picasso.with(mContext).load("https://www.google.rs/search?q=movie+png&source=lnms&tbm=isch&sa=X&ved=0ahUKEwjxlvTvk5HZAhWG1ywKHa7JCaMQ_AUICigB&biw=1366&bih=662#imgrc=dA5n-qcMZOjREM:").into(imageView, new Callback() {
                @Override
                public void onSuccess() {

                }

                @Override
                public void onError() {

                }
            });
        }

        return view;
    }
}
