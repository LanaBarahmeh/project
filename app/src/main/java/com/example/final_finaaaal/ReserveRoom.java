package com.example.final_finaaaal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
public class ReserveRoom extends AppCompatActivity {
    private ImageView slideshow;
    private AnimationDrawable animationDrawable;
    TextView text,desc;
    Button button;
    String roomId;
    Double price;
    String dateIn;
    String dateOut;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve_room);
        slideshow = findViewById(R.id.slideshow);

        System.err.println("sassssa");
        animationDrawable= (AnimationDrawable) slideshow.getDrawable();
        animationDrawable.start();
        Intent intent=getIntent();
        String roomtype=intent.getStringExtra("roomtype");
        String description=intent.getStringExtra("description");
        roomId= intent.getStringExtra("roomId");
        price=intent.getDoubleExtra("price",0);
        dateIn=intent.getStringExtra("dateIn");
        dateOut=intent.getStringExtra("dateOut");




        text=findViewById(R.id.text);
        desc=findViewById(R.id.description);
        button=findViewById(R.id.reservebutton);
        text.setText(roomtype);
        desc.setText(description + "\n"+ price);


        System.err.print("the room id is "+roomId);
        System.err.println("qwesss");
    }

    public void reserveRoomOnClick(View view) {
        System.err.print("the room id is "+roomId);
        Intent i=new Intent(ReserveRoom.this,UserInfo.class);
        i.putExtra("roomId",roomId);
        i.putExtra("dateIn",dateIn);
        i.putExtra("dateOut",dateOut);
        i.putExtra("price",price);


        startActivity(i);




    }
}