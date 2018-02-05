package com.example.home.movieapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.home.movieapp.helper.DataBaseHelperUser;
import com.example.home.movieapp.model.User;

//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.Task;
//import com.google.firebase.auth.AuthResult;
//import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etEmail,  etPassword, etConfirmPassword, etName, etUsername;
    DataBaseHelperUser helper = new DataBaseHelperUser(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //findViewById(R.id.btnRegister).setOnClickListener(this);
        findViewById(R.id.btnRegister).setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {

        if (v.getId()== R.id.btnRegister)
        {

            etEmail = (EditText) findViewById(R.id.etEmail);
            etPassword=(EditText) findViewById(R.id.etPassword);
            etConfirmPassword =(EditText) findViewById(R.id.etConfirmPassword);
            etName = (EditText) findViewById(R.id.etName);
            etUsername = (EditText) findViewById(R.id.etUsername);

            String emailStr = etEmail.getText().toString();
            String passwordStr = etPassword.getText().toString();
            String confirmPasswordStr = etConfirmPassword.getText().toString();
            String nameStr = etName.getText().toString();
            String usernameStr = etUsername.getText().toString();


            if(emailStr.isEmpty())
            {
                etEmail.setError("Email mora da se popuni");
                etEmail.requestFocus();
               return;
            } else if (!Patterns.EMAIL_ADDRESS.matcher(emailStr).matches())
            {
                etEmail.setError("Email nije dobar");
                etEmail.requestFocus();
                return;
            } else if (nameStr.isEmpty())
            {
                etName.setError("Ime mora da se popuni");
                etName.requestFocus();
                return;
            } else if (usernameStr.isEmpty())
            {
                etUsername.setError("Username mora da se popuni");
                etUsername.requestFocus();
                return;
            }   else if (passwordStr.isEmpty())
            {
                etPassword.setError("Password mora da se popuni");
                etPassword.requestFocus();
                return;
            } else if (confirmPasswordStr.isEmpty())
            {
                etConfirmPassword.setError("Confirm password mora da se popuni");
                etConfirmPassword.requestFocus();
                return;
            } else if(!passwordStr.equals(confirmPasswordStr))
            {
                Toast.makeText(RegisterActivity.this, "Paswordi se ne poklapaju", Toast.LENGTH_SHORT).show();
                return;
            }else

            {
                //Toast.makeText(RegisterActivity.this, "Korisnik je registrovan", Toast.LENGTH_SHORT).show();
                User user = new User();
                user.setEmail(emailStr);
                user.setName(nameStr);
                user.setUsername(usernameStr);
                user.setPassword(passwordStr);
                helper.insertUser(user);
                Toast.makeText(RegisterActivity.this, "Korisnik je registrovan", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, LoginActivity.class));

            }


        }

    }

}
