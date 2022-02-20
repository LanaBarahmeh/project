package com.example.final_finaaaal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class childService extends AppCompatActivity {

    CheckBox playingRoomBox,cyclingBox,paintRoomBox;
    String bookingId;
    int totalPrice;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child_service);

        Intent i=getIntent();
        bookingId=i.getStringExtra("bookingId");
        System.err.println("The booking id in the child service is "+bookingId);
        playingRoomBox=findViewById(R.id.playingRoomBox);
        cyclingBox=findViewById(R.id.cyclingBox);
        paintRoomBox=findViewById(R.id.paintingBox);



    }


    public void applyOnClick(View view) {
        updateBookingPrice();

        Toast.makeText(childService.this, "The Child Services added", Toast.LENGTH_SHORT).show();

    }


    private void updateBookingPrice(){

        if(playingRoomBox.isChecked()){
            totalPrice+=35;


        }

        if(paintRoomBox.isChecked()){
            totalPrice+=55;
        }
        if(cyclingBox.isChecked()){
            totalPrice+=70;

        }


        System.err.println("the total price is "+totalPrice);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://10.0.2.2/fnn/childService.php ", response -> {

                System.err.println("the respose is "+response);

    }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            error.printStackTrace();
            System.err.println("sssssssssssssssssss");
            Toast.makeText(childService.this, "wwww", Toast.LENGTH_SHORT).show();
        }
    })
    {
        @Override
        protected Map<String,String> getParams() throws AuthFailureError {
        Map<String,String>params = new HashMap<>();
        params.put("bookingId",bookingId);
        params.put("totalPrice",totalPrice+"");


        return params;
    }
    };

        Volley.newRequestQueue(childService.this).add(stringRequest);


}
}