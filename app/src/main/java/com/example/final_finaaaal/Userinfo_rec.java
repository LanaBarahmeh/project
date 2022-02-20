package com.example.final_finaaaal;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Userinfo_rec extends AppCompatActivity {
    TextView inDate;
    private String URL = "http://10.0.2.2/fnn/register.php";
    TextView outDate;
    double price;

    private EditText username, email,lastname;
    String roomId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userinfo_rec);
        System.err.println("LANAAAAAAAAAAAAAAAA");
        Intent i= getIntent();
        roomId = i.getStringExtra("roomId");
        price=i.getDoubleExtra("price",0);

        System.err.println("the room id is"+roomId);

        inDate = findViewById(R.id.indate);
        outDate = findViewById(R.id.outdate);
        username=findViewById(R.id.textviewfullnamerec);
        email=findViewById(R.id.textview_emailrec);
        lastname=findViewById(R.id.lastnamerec);
        //getting the calender from the device
        Calendar calendar = Calendar.getInstance();
        final  int year = calendar.get(Calendar.YEAR);
        final  int month = calendar.get(Calendar.MONTH);
        final  int day = calendar.get(Calendar.DAY_OF_MONTH);


        inDate.setOnClickListener(e->{
            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    Userinfo_rec.this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int day) {
                    month = month + 1;
                    String date = year + "-" + month + "-" + day;
                    inDate.setText(date);
                }
            }, year, month, day);
            datePickerDialog.show();


        });


        outDate.setOnClickListener(e->{
            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    Userinfo_rec.this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int day) {
                    month = month + 1;
                    String date = year + "-" + month + "-" + day;
                    outDate.setText(date);
                }
            }, year, month, day);
            datePickerDialog.show();
        });
    }
    public void bookreconclick(View view) {
        String name = this.username.getText().toString().trim();
        String email = this.email.getText().toString().trim();
        String lastname = this.lastname.getText().toString().trim();
        String dateIn= this.inDate.getText().toString().trim();
        String dateOut= this.outDate.getText().toString().trim();



        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, response -> {
            Toast.makeText(Userinfo_rec.this, response, Toast.LENGTH_SHORT).show();
            if(!response.equals("Error")) {

                Intent intent = new Intent(Userinfo_rec.this, MainActivity.class);
                
                startActivity(intent);
            }


        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
               System.err.println(error.getStackTrace());
            }
        })
        {
            @Override
            protected Map<String,String> getParams() throws AuthFailureError {
                Map<String,String>params = new HashMap<>();
                params.put("name",name);
                params.put("email",email);
                params.put("lastname",lastname);
                params.put("roomId",roomId);
                params.put("dateIn",dateIn);
                params.put("dateOut",dateOut);
                params.put("price",price+"");

                return params;
            }
        };

        Volley.newRequestQueue(Userinfo_rec.this).add(stringRequest);


    }

}