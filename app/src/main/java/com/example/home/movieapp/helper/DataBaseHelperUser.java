package com.example.home.movieapp.helper;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.home.movieapp.model.User;
import com.google.gson.Gson;

/**
 * Created by Home on 28.1.2018..
 */

public class DataBaseHelperUser extends SQLiteOpenHelper {

    String userObject;
    private static final int DATABASE_VERSION=1;
    private static final String DATABASE_NAME = "users.db";
    private static final String TABLE_NAME= "users";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_USERNAME= "username";
    private static final String COLUMN_PASSWORD= "password";
    SQLiteDatabase db;
    private static final String TABLE_CREATE = "create table users (id integer primary key auto_increment , " +
            "email text not null, name text not null, username text not null, password text not null);";


    public DataBaseHelperUser(Context context)
    {
        super(context , DATABASE_NAME, null, DATABASE_VERSION);
        //db.execSQL("drop table if exists "+ TABLE_NAME);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(TABLE_CREATE);
        this.db=db;
    }

    public void insertUser(User u)
    {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        String query="select * from users";
        Cursor cursor = db.rawQuery(query, null);
        //int count = cursor.getCount();
        //values.put(COLUMN_ID, count);
        values.put(COLUMN_EMAIL, u.getEmail());
        values.put(COLUMN_NAME, u.getName());
        values.put(COLUMN_USERNAME, u.getUsername());
        values.put(COLUMN_PASSWORD, u.getPassword());

        db.insert(TABLE_NAME, null, values);
        db.close();

    }

    public String searchPass(String username, String password)
    {
        db = this.getReadableDatabase();
        String query = "select * from "+TABLE_NAME+" where username='"+username+"' and password='"+password+"'";
        Cursor cursor = db.rawQuery(query, null);
        String usernameStr, passwordStr;
        passwordStr="nije pronadjen password";
        if(cursor.moveToFirst())
        {
            do {
                usernameStr=cursor.getString(cursor.getColumnIndex("username"));
                System.out.println(cursor.getString(cursor.getColumnIndex("email")));
                User user = new User();
                user.setId(cursor.getString(cursor.getColumnIndex("id")));
                user.setEmail(cursor.getString(cursor.getColumnIndex("email")));
                user.setName(cursor.getString(cursor.getColumnIndex("name")));
                user.setUsername(cursor.getString(cursor.getColumnIndex("username")));
                user.setPassword(cursor.getString(cursor.getColumnIndex("password")));
                Gson gson = new Gson();
                userObject = gson.toJson(user);


                if(usernameStr.equals(username))
                {
                    passwordStr= cursor.getString(cursor.getColumnIndex("password"));
                    break;
                }
            } while(cursor.moveToNext());
        }
        return userObject;
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String query = "DROP TABLE IF EXISTS "+TABLE_NAME;
        db.execSQL(query);
        this.onCreate(db);

    }
}
