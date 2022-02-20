package com.example.final_finaaaal;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;
public class UserInfo extends AppCompatActivity {
    private EditText username, email,lastname;
    private Button book;
    private String URL = "http://10.0.2.2/fnn/register.php";
    String roomId;
    double price;
    String dateIn;
    String dateOut;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        username = findViewById(R.id.textviewfullname);
        email = findViewById(R.id.textview_email);
        book = findViewById(R.id.book);
        Intent i = getIntent();
        roomId = i.getStringExtra("roomId");
        dateIn=i.getStringExtra("dateIn");
        dateOut=i.getStringExtra("dateOut");
        price=i.getDoubleExtra("price",0);




        System.err.println("User info id " + roomId+ " the date in is "+dateIn+" the date out is "+dateOut+
                " the price is "+price );

        lastname=findViewById(R.id.lastname);


//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.gender, android.R.layout.simple_spinner_item);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinner.setAdapter(adapter);
//        spinner.setOnItemSelectedListener(this);
    }



    private void Addinfo(){
        String name = this.username.getText().toString().trim();
        String email = this.email.getText().toString().trim();
        String lastname = this.lastname.getText().toString().trim();
        String roomid=this.roomId.trim();
        System.err.println("the room id is"+roomid);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, response -> {



            Toast.makeText(UserInfo.this, response, Toast.LENGTH_SHORT).show();


            if(!response.equals("Error")) {


                Intent intent = new Intent(UserInfo.this, extra_services.class);
                intent.putExtra("bookingId",response);

                startActivity(intent);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        })
        {
            @Override
            protected Map<String,String> getParams() throws AuthFailureError {
                Map<String,String>params = new HashMap<>();
                params.put("name",name);
                params.put("email",email);
                params.put("lastname",lastname);
                params.put("roomId",roomid);
                params.put("dateIn",dateIn);
                params.put("dateOut",dateOut);
                params.put("price",price+"");


                return params;
            }
        };

        Volley.newRequestQueue(UserInfo.this).add(stringRequest);

    }
    public void bookonclick(View view) {
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                Addinfo();

            }
        });
    }



}