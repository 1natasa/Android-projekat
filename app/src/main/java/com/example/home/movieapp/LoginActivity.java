package com.example.home.movieapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.util.Patterns;

import com.example.home.movieapp.helper.DataBaseHelperMovie;
import com.example.home.movieapp.helper.DataBaseHelperUser;
import com.example.home.movieapp.model.User;
import com.google.gson.Gson;

public class LoginActivity extends AppCompatActivity implements  View.OnClickListener{


    DataBaseHelperUser helper = new DataBaseHelperUser(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        findViewById(R.id.tvRegisterHere).setOnClickListener(this);
        findViewById(R.id.btnLogin).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {

            //ako je kliknuto dugme tvRegisterHere otvoricemo novu Activity class-u da korisnik moze da se registruje
            case R.id.tvRegisterHere:

                startActivity(new Intent(this, RegisterActivity.class));
                break;

            case R.id.btnLogin:
                //startActivity(new Intent(this, UserAreaActivity.class));
                EditText etUsername = (EditText)findViewById(R.id.etUsername);
                String usernameStr = etUsername.getText().toString();

                EditText etPassword = (EditText)findViewById(R.id.etPassword);
                String passStr = etPassword.getText().toString();

                String userObject = helper.searchPass(usernameStr, passStr);
                //SharedPreferences sluzi samo za citanje, a pomocu editora cu upisati usera u local storge
                SharedPreferences sharedPreferences = getSharedPreferences("shared preferences",MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                System.out.println(userObject);
                editor.putString("user",userObject);
                editor.apply();
                Gson gson = new Gson();
                User user = gson.fromJson(userObject,User.class);

                if(userObject==null)
                {

                    Toast.makeText(LoginActivity.this, "Login error", Toast.LENGTH_SHORT).show();

                    return;

                }
                if (passStr.equals(user.getPassword()))
                {
                    startActivity(new Intent(this, UserAreaActivity.class));
                } else
                {
                   // Toast.makeText(LoginActivity.this, "Password and username dont match", Toast.LENGTH_SHORT).show();
                }
                break;

        }

    }
}
