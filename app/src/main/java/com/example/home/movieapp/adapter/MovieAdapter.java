package com.example.home.movieapp.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.home.movieapp.R;
import com.example.home.movieapp.model.Movie;

import java.util.List;

/**
 * Created by Home on 2.2.2018..
 */

public class MovieAdapter extends BaseAdapter {

    private Context mContext;
    private List<Movie> mMovie;

    public MovieAdapter(Context mContext, List<Movie> mMovie) {
        this.mContext = mContext;
        this.mMovie = mMovie;
    }

    @Override
    public int getCount() {
        return mMovie.size();
    }

    @Override
    public Object getItem(int position) {
        return mMovie.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = View.inflate(mContext, R.layout.movie_item, null);
        TextView tvTitle =(TextView) view.findViewById(R.id.tvMovieTitle);
        TextView tvRate =(TextView) view.findViewById(R.id.tvMovieRate);
        tvTitle.setText(mMovie.get(position).getTitle());
        tvRate.setText(String.valueOf(mMovie.get(position).getImdbRating()));

        //view.setTag(mMovie.get(position).getId());
        return view;
    }
}
