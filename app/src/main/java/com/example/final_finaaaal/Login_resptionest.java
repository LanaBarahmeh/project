package com.example.final_finaaaal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class Login_resptionest extends AppCompatActivity {

    Button login;
    private String sname,spassword;
    private EditText username ,password;
    private CheckBox checkbox;
    private String URL = "http://10.0.2.2/fnn/loginRec.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_resptionest);

        sname=spassword="";
        username =  findViewById(R.id.username);
        password =  findViewById(R.id.password);
        checkbox = findViewById(R.id.checkBox);
        login = findViewById(R.id.loginbt);


    }




    public void Login(String name , String password ){

//            login.setVisibility(View.GONE);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Toast.makeText(Login_resptionest.this , response,Toast.LENGTH_SHORT ).show();

                if(response.equals("true")) {


                    Intent i = new Intent(Login_resptionest.this, Reciption_Options.class);
                    startActivity(i);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Login_resptionest.this , "error"+error.toString(), Toast.LENGTH_LONG).show();
                System.err.println(error.toString());
            }
        }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String>params = new HashMap<>();
                params.put("name",name);
                params.put("password",password);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);



    }

    public void logInResptionestOnClick(View view) {

        sname=username.getText().toString().trim();
        spassword=password.getText().toString().trim();
        if(!sname.isEmpty()&&!spassword.isEmpty()){
            if(checkbox.isChecked()){
                SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
                SharedPreferences.Editor editor= pref.edit();
                editor.putString("name",sname);
                editor.putString("password",spassword);
                editor.commit();
                Toast.makeText(this, "Your data is saved",Toast.LENGTH_SHORT ).show();
            }

            Login(sname,spassword);

        }else{
            username.setError("Please insert name");
            password.setError("please insert password");
        }

    }
}