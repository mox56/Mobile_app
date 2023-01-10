package com.example.myapplication;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity  extends AppCompatActivity {

    TextView helloUser;

    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        helloUser = (TextView) findViewById(R.id.helloUser);

        Bundle extras = getIntent() . getExtras();
        String username = null;
        if(extras !=null){
            username = extras.getString("name");
            helloUser.setText("Welcome" + username);
        }

    }
}
