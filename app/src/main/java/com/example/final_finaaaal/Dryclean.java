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

public class Dryclean extends AppCompatActivity {
    CheckBox checkbox1,checkbox2;
    String bookingId;
    int totalPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dryclean);

        Intent i=getIntent();
        bookingId=i.getStringExtra("bookingId");
        System.err.println("The booking id in the Dry clean service is "+bookingId);
        checkbox1=findViewById(R.id.firstcheck);
        checkbox2=findViewById(R.id.secondcheck);
    }

    public void applyonclick(View view) {
        updateBookingPrice();

        Toast.makeText(Dryclean.this, "The Dry clean services are added", Toast.LENGTH_SHORT).show();

    }
    private void updateBookingPrice(){

        if(checkbox1.isChecked()){
            totalPrice+=5;
        }

        if(checkbox2.isChecked()){
            totalPrice+=10;
        }


        System.err.println("the total price is "+totalPrice);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://10.0.2.2/fnn/childService.php ", response -> {

            System.err.println("the response is "+response);

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                System.err.println("ss2222");

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

        Volley.newRequestQueue(Dryclean.this).add(stringRequest);


    }
}