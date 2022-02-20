package com.example.final_finaaaal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class Search_customer extends AppCompatActivity {
    private RecyclerView recycler;
    private final String BASE_URL = "http://10.0.2.2/connection.php";
    private List<Room> items = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_customer);
        recycler=(RecyclerView) findViewById(R.id.searchrecycler);

        Intent intent = getIntent();
        Bundle args = intent.getBundleExtra("BUNDLE");
        items = (ArrayList<Room>) args.getSerializable("ARRAYLIST");

        String inDate=intent.getStringExtra("inDate");
        String outDate=intent.getStringExtra("outDate");

        System.err.println("the in date is "+inDate);

        //Room rooms[]=Room.getRooms();
        recycler.setLayoutManager(new LinearLayoutManager(this));
        // CaptionImagesAdapter adapter=new CaptionImagesAdapter(rooms,this);
        Adapter2 adapter = new Adapter2(items,Search_customer.this,inDate,outDate);
        recycler.setAdapter(adapter);
        // recycler.setAdapter(adapter);


    }
}