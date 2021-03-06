package com.example.home.movieapp.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Parcel;

import com.example.home.movieapp.model.Movie;
import com.example.home.movieapp.model.User;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Home on 3.2.2018..
 */

public class DataBaseHelperMovie extends SQLiteOpenHelper {

    SQLiteDatabase db;
    private static final int DATABASE_VERSION=1;
    private static final String DATABASE_NAME = "users.db";
    private static final String TABLE_NAME= "movie";
    private static final String COLUMN_ID = "id_movie";
    private static final String COLUMN_TITLE = "imdb_title";
    private static final String COLUMN_RATE = "my_rate";
    private static final String COLUMN_COMMENT= "my_comment";
    private static final String COLUMN_IMDB_YEAR= "imdb_year";
    private static final String COLUMN_IMDB_ACTORS="imdb_actors";
    private static final String COLUMN_IMDB_AWARDS="imdb_awards";
    private static final String COLUMN_IMDB_DIRECTOR="imdb_director";
    private static final String COLUMN_IMDB_GENRE="imdb_genre";
    private static final String COLUMN_IMDB_RATING="imdb_rating";
    private static final String COLUMN_IMDB_POSTER="imdb_poster";



    //private static  final String TABLE_CREATE ="create table movie(id_movie integer primary key not null )";

    public DataBaseHelperMovie(Context context) {

        super(context , DATABASE_NAME, null, DATABASE_VERSION);
        //db.execSQL(TABLE_CREATE);

    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        this.db=db;

    }

    public void insertMovie(Movie m) throws JSONException {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        //parcel je tip podatka, on ima metodu koja moze da upisuje mapu u bazu, a njega ne moze direktno u bazu, vec
        //preko content velues
        Parcel parcel = Parcel.obtain();
        Gson gson = new Gson();
        //ako ne moze da ga konvertuje da izbaci gresku
        JSONObject json = new JSONObject(gson.toJson(m));
        Map<String,Object> map = MapModel.jsonToMap(json);
        parcel.writeMap(map);
        parcel.setDataPosition(0);
        ContentValues contentValues = values.CREATOR.createFromParcel(parcel);


       /* values.put(COLUMN_TITLE, m.getTitle());
        values.put(COLUMN_IMDB_YEAR,m.getYear() );
        values.put(COLUMN_IMDB_ACTORS, m.getActors());
        values.put(COLUMN_IMDB_AWARDS, m.getAwards());
        values.put(COLUMN_IMDB_DIRECTOR,m.getDirector());
        values.put(COLUMN_IMDB_GENRE, m.getGenre());
        values.put(COLUMN_IMDB_RATING,m.getImdbRating());
        values.put(COLUMN_IMDB_POSTER,m.getPoster());*/
        System.out.println(contentValues);
        db.insert(TABLE_NAME, null, contentValues);
        db.close();

    }

    public List<Movie> getAll(Integer userid)
    {
        ArrayList<Movie> list = new ArrayList<>();
        String selectQuery="SELECT * FROM " + TABLE_NAME + " where USERID="+ userid + " and WATCHED=1";
        SQLiteDatabase db = this.getReadableDatabase();
        try{
            Cursor cursor = db.rawQuery(selectQuery, null);
            try{
                if(cursor.moveToFirst())
                {
                    do{
                        Movie movie = new Movie();
                        movie.setTitle(cursor.getString(cursor.getColumnIndex("TITLE")));
                        movie.setImdbRating(Double.valueOf(cursor.getString(cursor.getColumnIndex("IMDBRATING"))));
                        movie.setPoster(cursor.getString(cursor.getColumnIndex("POSTER")));
                        movie.setMyRate(cursor.getString(cursor.getColumnIndex("MY_RATE")));
                        movie.setMyComment(cursor.getString(cursor.getColumnIndex("MY_COMMENT")));
                        movie.setYear(cursor.getString(cursor.getColumnIndex("YEAR")));
                        movie.setGenre(cursor.getString(cursor.getColumnIndex("GENRE")));
                        movie.setActors(cursor.getString(cursor.getColumnIndex("ACTORS")));
                        movie.setAwards(cursor.getString(cursor.getColumnIndex("AWARDS")));
                        movie.setDirector(cursor.getString(cursor.getColumnIndex("DIRECTOR")));



                        list.add(movie);
                        //movie.setTitle(cursor.getString(cursor.getColumnIndex("TITLE")));
                        /*movie.setTitle(cursor.getString(cursor.getColumnIndex("TITLE")));
                        movie.setTitle(cursor.getString(cursor.getColumnIndex("TITLE")));
                        movie.setTitle(cursor.getString(cursor.getColumnIndex("TITLE")));*/
                    } while (cursor.moveToNext());
                }
            } finally {
                try{cursor.close();}
                catch (Exception ignore){}
            }
        }finally {
            try {
                db.close();
            }
            catch (Exception ignore){}
        }
        return list;
    }

    public void deleteMovie(String imbdTitle)
    {
        db = this.getWritableDatabase();
        db.delete(TABLE_NAME, "TITLE=?", new String[] {imbdTitle});
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS "+TABLE_NAME;
        db.execSQL(query);
        this.onCreate(db);
    }

    public List<Movie> getAllToWatch(Integer userid)
    {
        ArrayList<Movie> list = new ArrayList<>();
        String selectQuery="SELECT * FROM " + TABLE_NAME + " where USERID="+ userid + " and WATCHED=0";
        SQLiteDatabase db = this.getReadableDatabase();
        try{
            Cursor cursor = db.rawQuery(selectQuery, null);
            try{
                if(cursor.moveToFirst())
                {
                    do{
                        Movie movie = new Movie();
                        movie.setTitle(cursor.getString(cursor.getColumnIndex("TITLE")));
                        movie.setImdbRating(Double.valueOf(cursor.getString(cursor.getColumnIndex("IMDBRATING"))));
                        movie.setPoster(cursor.getString(cursor.getColumnIndex("POSTER")));
                        movie.setMyRate(cursor.getString(cursor.getColumnIndex("MY_RATE")));
                        movie.setMyComment(cursor.getString(cursor.getColumnIndex("MY_COMMENT")));
                        movie.setYear(cursor.getString(cursor.getColumnIndex("YEAR")));
                        movie.setGenre(cursor.getString(cursor.getColumnIndex("GENRE")));
                        movie.setActors(cursor.getString(cursor.getColumnIndex("ACTORS")));
                        movie.setAwards(cursor.getString(cursor.getColumnIndex("AWARDS")));
                        movie.setDirector(cursor.getString(cursor.getColumnIndex("DIRECTOR")));



                        list.add(movie);

                    } while (cursor.moveToNext());
                }
            } finally {
                try{cursor.close();}
                catch (Exception ignore){}
            }
        }finally {
            try {
                db.close();
            }
            catch (Exception ignore){}
        }
        return list;
    }

    public void update(Movie movie) throws JSONException {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        //parcel je tip podatka, on ima metodu koja moze da upisuje mapu u bazu, a njega ne mogu direktno u bazy, vec
        //preko content velues
        Parcel parcel = Parcel.obtain();
        Gson gson = new Gson();
        //ako ne moze da ga konvertuje da izbaci gresku
        JSONObject json = new JSONObject(gson.toJson(movie));
        Map<String,Object> map = MapModel.jsonToMap(json);
        parcel.writeMap(map);
        parcel.setDataPosition(0);
        ContentValues contentValues = values.CREATOR.createFromParcel(parcel);
        db.update(TABLE_NAME, contentValues,"TITLE=?", new String[]{movie.getTitle()} );
    }
}
