package com.example.capstoneapp;

import android.icu.text.Edits;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URL;
import java.util.Iterator;

public class lot1 extends AppCompatActivity implements ApiResponse {

    ApiTask apitask = new ApiTask();
    JSONObject jsonObject;
    JSONArray jsonArray = new JSONArray();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lot1);

        apitask.response = this;
        apitask.execute("https://sheets.googleapis.com/v4/spreadsheets/1OKpw2Mu4Sbq7vOcW0zEfy0984ZQCurxX9md10_a2Qng/values/Sheet1!A:D?key=AIzaSyB2Pc9L071Y5v5SVTugvyRz7LWz1Z8psgY");
    }

    @Override
    public void processResult(String output){
        try {
            jsonObject = new JSONObject(output);
            jsonArray = (jsonObject.getJSONArray("values"));
            Log.d("JSONARRAY", jsonArray.toString());
        }
        catch (Exception e){
            Log.e("EXCEPTION", "Process result" ,e );
        }

    }

    public void back(View view){
        finish();
    }
   
}
