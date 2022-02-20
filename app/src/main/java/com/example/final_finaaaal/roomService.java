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

public class roomService extends AppCompatActivity {
    CheckBox checkbox1,checkbox2,checkbox3,checkbox4;
    String bookingId;
    int totalPrice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_service);
        Intent i=getIntent();
        bookingId=i.getStringExtra("bookingId");
        System.err.println("The booking id in the child service is "+bookingId);
        checkbox1=findViewById(R.id.checkbox1);
        checkbox2=findViewById(R.id.checkbox2);
        checkbox3=findViewById(R.id.checkbox3);
        checkbox4=findViewById(R.id.checkbox4);

    }
    public void applyonClick(View view) {
        updateBookingPrice();

        Toast.makeText(roomService.this, "The Room services are added", Toast.LENGTH_SHORT).show();

    }


    private void updateBookingPrice(){

        if(checkbox1.isChecked()){
            totalPrice+=80;
        }

        if(checkbox2.isChecked()){
            totalPrice+=60;
        }
        if(checkbox3.isChecked()){
            totalPrice+=40;

        }
        if(checkbox4.isChecked()){
            totalPrice+=35;

        }

        System.err.println("the total price is "+totalPrice);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://10.0.2.2/fnn/childService.php ", response -> {

            System.err.println("the response is "+response);

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                System.err.println("ss2222");
              //  Toast.makeText(roomService.this, "wwww", Toast.LENGTH_SHORT).show();
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

        Volley.newRequestQueue(roomService.this).add(stringRequest);


    }
}