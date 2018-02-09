package com.example.home.movieapp.adapter;

import android.content.Context;
import android.provider.ContactsContract;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.home.movieapp.R;
import com.example.home.movieapp.RegisterActivity;
import com.example.home.movieapp.WatchedActivity;
import com.example.home.movieapp.helper.DataBaseHelperMovie;
import com.example.home.movieapp.model.Movie;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;


import java.util.List;

/**
 * Created by Home on 2.2.2018..
 */

public class MovieAdapter extends BaseAdapter {

    //mCocetx je contex od ovoga, gde god pozovem adapter kroz konstruktor mu dam kontekst
    private Context mContext;
    private List<Movie> mMovie;
    DataBaseHelperMovie db;

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
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = View.inflate(mContext, R.layout.movie_item, null);
        TextView tvTitle =(TextView) view.findViewById(R.id.tvMovieTitle);
        TextView tvRate =(TextView) view.findViewById(R.id.tvMovieRate);
        tvTitle.setText(mMovie.get(position).getTitle());
        tvRate.setText(String.valueOf(mMovie.get(position).getImdbRating()));
        ImageView imageView =(ImageView) view.findViewById(R.id.movie_image);
        ImageButton btnDelete = (ImageButton) view.findViewById(R.id.imageButtonDelete);
        btnDelete.setFocusable(false);
        db=new DataBaseHelperMovie(mContext);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                db.deleteMovie(mMovie.get(position).getTitle());
                Toast.makeText(mContext, "Movie deleted", Toast.LENGTH_SHORT).show();
                mMovie.remove(position);
                notifyDataSetChanged();


            }
        });


        if(!mMovie.get(position).getPoster().equals("N/A"))
        {
            Picasso.with(mContext).load(mMovie.get(position).getPoster()).into(imageView, new Callback() {
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




        //view.setTag(mMovie.get(position).getId());
        return view;
    }
}
