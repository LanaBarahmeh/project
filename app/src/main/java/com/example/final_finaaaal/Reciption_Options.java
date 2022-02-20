package com.example.final_finaaaal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Reciption_Options extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reciption_options);



    }

    public void recSearchForRoomsOnClick(View view) {

        Intent i =new Intent(Reciption_Options.this,Search_rec.class);
        startActivity(i);

    }
}