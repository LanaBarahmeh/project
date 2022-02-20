package com.example.final_finaaaal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class extra_services extends AppCompatActivity {
    Button childbtn , dryclean;
    String bookingId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extra_services);
        dryclean =findViewById(R.id.button1);
        childbtn=findViewById(R.id.button2);
        Intent i =getIntent();

        bookingId=i.getStringExtra("bookingId");
        System.err.println("the booking id in the extra services is "+bookingId);

    }



    public void childServiceOnClick(View view){

        Intent intent= new Intent(extra_services.this,childService.class);
        intent.putExtra("bookingId",bookingId);
        startActivity(intent);
    }

    public void roomServicesOnClick(View view){
        Intent intent= new Intent(extra_services.this,roomService.class);
        intent.putExtra("bookingId",bookingId);
        startActivity(intent);

    }
    public void dryCleanServicesOnClick(View view){
        Intent intent= new Intent(extra_services.this,Dryclean.class);
        intent.putExtra("bookingId",bookingId);
        startActivity(intent);

    }
}