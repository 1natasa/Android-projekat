package com.example.home.movieapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.home.movieapp.helper.DataBaseHelperUser;
import com.example.home.movieapp.model.User;
import com.google.gson.Gson;

public class LoginActivity extends AppCompatActivity implements  View.OnClickListener{

    DataBaseHelperUser helper = new DataBaseHelperUser(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

      /*  final EditText etUsername = (EditText) findViewById(R.id.etUsername);
        final EditText etPassword =(EditText) findViewById(R.id.etPassword);
        final Button btnLogin = (Button) findViewById(R.id.btnLogin);*/
       // final TextView registerLink = (TextView) findViewById(R.id.tvRegisterHere);

       /* registerLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                LoginActivity.this.startActivity(registerIntent);

            }
        });*/
        findViewById(R.id.tvRegisterHere).setOnClickListener(this);
        findViewById(R.id.btnLogin).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {

            //ako je kliknuto dugme tvRegisterHere otvoricemo novu Activity classu da korisnik moze da se registruje
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
                editor.putString("user",userObject);
                editor.apply();
                Gson gson = new Gson();
                User user = gson.fromJson(userObject,User.class);


                if (passStr.equals(user.getPassword()))
                {
                    startActivity(new Intent(this, UserAreaActivity.class));
                } else
                {
                    Toast.makeText(LoginActivity.this, "Password ne odgovara unetom usernameu", Toast.LENGTH_SHORT).show();
                }
                break;

        }


       /* if(v.getId() == R.id.tvRegisterHere)
        {
            startActivity(new Intent(this, RegisterActivity.class));

        } else if (v.getId() == R.id.btnLogin)
        {

            //startActivity(new Intent(this, UserAreaActivity.class));
            EditText etUsername = (EditText)findViewById(R.id.etUsername);
            String usernameStr = etUsername.getText().toString();

            EditText etPassword = (EditText)findViewById(R.id.etPassword);
            String passStr = etPassword.getText().toString();

            String password = helper.searchPass(usernameStr);

            if (passStr.equals(password))
            {
                startActivity(new Intent(this, UserAreaActivity.class));
            } else
            {
                Toast.makeText(LoginActivity.this, "Password ne odgovara unetom usernamey", Toast.LENGTH_SHORT).show();
            }

        }*/

    }
}
