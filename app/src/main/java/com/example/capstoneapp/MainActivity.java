package com.example.capstoneapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onclick1(View view){
        Intent intent = new Intent(getApplicationContext(),lot1.class);
        startActivity(intent);
    }

    public void onclick2(View view){
        Intent intent = new Intent(getApplicationContext(),lot2.class);
        startActivity(intent);
    }

    public void onclick3(View view){
        Intent intent = new Intent(getApplicationContext(),lot3.class);
        startActivity(intent);
    }
}