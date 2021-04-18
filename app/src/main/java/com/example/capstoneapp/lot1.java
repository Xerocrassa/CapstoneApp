package com.example.capstoneapp;

import android.graphics.Color;
import android.icu.text.Edits;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URL;
import java.util.Iterator;

public class lot1 extends AppCompatActivity implements ApiResponse {

    ApiTask apitask = new ApiTask();
    JSONObject jsonObject;
    JSONArray jsonArray = new JSONArray();
    TextView title, space1, space2, space3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lot1);
        title = findViewById(R.id.TitleLot1);
        space1 = findViewById(R.id.lot1space1);
        space2 = findViewById(R.id.lot1space2);
        space3 = findViewById(R.id.lot1space3);


        apitask.response = this;
        apitask.execute("https://sheets.googleapis.com/v4/spreadsheets/1OKpw2Mu4Sbq7vOcW0zEfy0984ZQCurxX9md10_a2Qng/values/Sheet1!A2:E7?key=AIzaSyB2Pc9L071Y5v5SVTugvyRz7LWz1Z8psgY");
    }

    @Override
    public void processResult(String output){
        try {
            jsonObject = new JSONObject(output);
            jsonArray = (jsonObject.getJSONArray("values"));
            String space1status, space2status, space3status;
            int distanceindex = 0;
            int mindistance = Integer.MAX_VALUE;
            space1status = jsonArray.getJSONArray(3).getString(4);
            space2status = jsonArray.getJSONArray(4).getString(4);
            space3status = jsonArray.getJSONArray(5).getString(4);
            if (space1status.equals("Occupied") && space2status.equals("Occupied") && space3status.equals("Occupied")){
                title.setText("This lot is full. Please try using another lot.");
                space1.setBackgroundColor(Color.parseColor("#B30000"));
                space2.setBackgroundColor(Color.parseColor("#B30000"));
                space3.setBackgroundColor(Color.parseColor("#B30000"));
            }
            else {
                if (space1status.equals("Empty")) {
                    space1.setBackgroundColor(Color.parseColor("#009900"));
                }
                else{
                    space1.setBackgroundColor(Color.parseColor("#B30000"));
                }
                if (space2status.equals("Empty")) {
                    space2.setBackgroundColor(Color.parseColor("#009900"));
                }
                else{
                    space2.setBackgroundColor(Color.parseColor("#B30000"));
                }
                if (space3status.equals("Empty")) {
                    space3.setBackgroundColor(Color.parseColor("#009900"));
                }
                else{
                    space3.setBackgroundColor(Color.parseColor("#B30000"));
                }

                for (int i = 3; i < 6; i++) {
                    if (jsonArray.getJSONArray(i).getString(4).equals("Empty")) {
                        if (jsonArray.getJSONArray(i).getInt(2) < mindistance) {
                            distanceindex = i;
                            mindistance = jsonArray.getJSONArray(i).getInt(2);
                        }
                    }
                }
                switch (distanceindex){
                    case 3:
                        space1.setBackgroundColor(Color.parseColor("#3333ff"));
                        break;
                    case 4:
                        space2.setBackgroundColor(Color.parseColor("#3333ff"));
                        break;
                    case 5:
                        space3.setBackgroundColor(Color.parseColor("#3333ff"));
                        break;
                }

                title.setText("The closest available space is space " + jsonArray.getJSONArray(distanceindex).getString(1) + " with a distance of " + mindistance + " meters away. The space is marked in blue");
            }



        }
        catch (Exception e){
            Log.e("EXCEPTION", "Process result" ,e );
        }

    }

    public void back(View view){
        finish();
    }
   
}
