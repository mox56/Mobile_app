package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

        EditText username,password;
        Button loginbtn;
        DBHelper dbHelper;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        username=(EditText) findViewById(R.id.username);
        password=(EditText) findViewById(R.id.password);
        loginbtn= (Button) findViewById(R.id.loginbtn);

        dbHelper = new DBHelper(LoginActivity.this);

        loginbtn.setOnClickListener(new View.OnClickListener(){
            @Override
                public void onClick(View v){
                    boolean isExist = dbHelper.checkUserExist(username.getText().toString(), password.getText().toString());

                    if(isExist){
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        intent.putExtra("name", username.getText().toString());
                        startActivity(intent);
                    } else
                        password.setText(null);
                        Toast.makeText(LoginActivity.this, "Login failed. Invalid username or password.", Toast.LENGTH_SHORT).show();
            }
            });
        }
        }