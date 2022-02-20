package com.example.final_finaaaal;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.Intent;
import android.os.Bundle;


import android.app.DatePickerDialog;
import android.graphics.drawable.AnimationDrawable;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static androidx.core.content.PermissionChecker.PERMISSION_GRANTED;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

public class MainActivity extends AppCompatActivity {

    private ImageView slideshow;
    private AnimationDrawable animationDrawable;

    private static final String ERROR_MSG="google play services are unavaliable";
    private static final int LOCATION_PERMISSION_REQUEST = 1;

    TextView inDate;
    TextView outDate;
    Button b;
    Context c;

    DatePickerDialog.OnDateSetListener setListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        slideshow = findViewById(R.id.slideshow);
        animationDrawable= (AnimationDrawable) slideshow.getDrawable();
        animationDrawable.start();

        inDate = findViewById(R.id.inDate);
        outDate = findViewById(R.id.outDate);

        b=findViewById(R.id.taxibtn);

        GoogleApiAvailability availability = GoogleApiAvailability.getInstance();

        int result = availability.isGooglePlayServicesAvailable(this);
        if (result != ConnectionResult.SUCCESS) {
            if (!availability.isUserResolvableError(result)) {
                Toast.makeText(this, "Error in the on create", Toast.LENGTH_LONG).show();
            }
        }

        c  = this;




        //getting the calender from the device
        Calendar calendar = Calendar.getInstance();
        final  int year = calendar.get(Calendar.YEAR);
        final  int month = calendar.get(Calendar.MONTH);
        final  int day = calendar.get(Calendar.DAY_OF_MONTH);


        inDate.setOnClickListener(e->{
            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    MainActivity.this, new DatePickerDialog.OnDateSetListener() {
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
                    MainActivity.this, new DatePickerDialog.OnDateSetListener() {
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

    public void searchForRoomsOnClick(View view) {



        if(inDate.getText().toString().isEmpty()||outDate.getText().toString().isEmpty()){
            Toast.makeText(this, "Please fill the dates", Toast.LENGTH_LONG).show();

        }else{




            String []inDateArray=inDate.getText().toString().split("-");

            String dayInString =inDateArray[2];
            dayInString=checkLength(dayInString);

            String monthInString=inDateArray[1];
            monthInString=checkLength(monthInString);

            String yearInString=inDateArray[0];

            String newInDate=yearInString+"-"+monthInString+"-"+dayInString;



            String []outDateArray=outDate.getText().toString().split("-");

            String dayOutString =outDateArray[2];
            dayOutString=checkLength(dayOutString);

            String monthOutString =outDateArray[1];
            monthOutString=checkLength(monthOutString);

            String yearOutString= outDateArray[0];

            String newOutDate=yearOutString+"-"+monthOutString+"-"+dayOutString;






            if(newOutDate.compareTo(newInDate)<=0){
                Toast.makeText( getApplicationContext(),  "The check out date must be after the check in date", Toast.LENGTH_LONG).show();
                System.err.println("false input");

            }else{
                String url = "http://10.0.2.2/fnn/getEmptyRooms.php?dateIn="+newInDate + "&dateOut=" +newOutDate;

                System.err.println(url);
                final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                        (Request.Method.GET, url,
                                null, new Response.Listener<JSONObject>() {

                            @Override
                            public void onResponse(JSONObject response) {

                                try {
                                    JSONArray array = response.getJSONArray("rooms");
//                                    System.err.println(array.toString());

                                    ArrayList<Room> rooms = new ArrayList<>();

                                    for(int i=0;i<array.length();i++){
                                        JSONObject obj = array.getJSONObject(i);
                                        try {



                                            boolean status=false;
                                            if(obj.getInt("status")==1){
                                                status=true;

                                            }else if(obj.getInt("status")==0){
                                                status=false;

                                            }


                                            Room room=new Room(obj.getInt("room_id"),status,obj.getString("description"),
                                                    obj.getInt("floor"),obj.getString("room_photo"),obj.getString("room_type"),obj.getDouble("price"));
                                            rooms.add(room);
                                            System.err.println(rooms);
                                            Intent intent = new Intent(MainActivity.this, Search_customer.class);
                                            Bundle args = new Bundle();
                                            args.putSerializable("ARRAYLIST",(Serializable)rooms);
                                            intent.putExtra("BUNDLE",args);
                                            intent.putExtra("inDate",newInDate);
                                            intent.putExtra("outDate",newOutDate);
                                            startActivity(intent);



                                        }
                                        catch (Exception e){
                                            System.err.println("my error");
                                            e.printStackTrace();
                                        }


                                    }




                                } catch (JSONException e) {
                                    Toast.makeText(MainActivity.this, "bbbbb", Toast.LENGTH_SHORT).show();
                                    e.printStackTrace();
                                }


                            }
                        }, new Response.ErrorListener() {

                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(MainActivity.this, "On error response", Toast.LENGTH_SHORT).show();

                                error.printStackTrace();
                            }
                        });
                MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);


            }


        }
    }

    public static String checkLength(String str){

        if(str.length()==1){
            str="0"+str;

        }
        return str;
    }


    public void logInMainPageOnClick(View view) {
        Intent i =new Intent(MainActivity.this,Login_resptionest.class);
        startActivity(i);
    }



    public void getTaxtOnClick(View view){

        // Check if we have permission to access high accuracy fine location.
        int permission = ActivityCompat.checkSelfPermission(this, ACCESS_FINE_LOCATION);

        // If permission is granted, fetch the last location.
        if (permission == PERMISSION_GRANTED) {
            System.err.print("dddddhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhd");
            FusedLocationProviderClient fusedLocationClient;
            fusedLocationClient =
                    LocationServices.getFusedLocationProviderClient(this);
            if (ActivityCompat.checkSelfPermission(this, ACCESS_FINE_LOCATION)
                    == PERMISSION_GRANTED ||
                    ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                            == PERMISSION_GRANTED) {
                fusedLocationClient.getLastLocation()
                        .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                            @Override
                            public void onSuccess(Location location) {
                                System.err.println("in");

                                Geocoder geocoder = new Geocoder(c);
                                try{
                                    List<Address> address = geocoder.getFromLocation(location.getLatitude(),
                                            location.getLongitude(), 1);
                                    String finaladdress=address.get(0).getAddressLine(0);
                                    System.err.println("final address"+finaladdress);

//                                    connectingWithDb(finaladdress);

                                }catch(Exception e){

                                }


                            }
                        });

            }
        } else {
            // If permission has not been granted, request permission.
            ActivityCompat.requestPermissions(this,
                    new String[]{ACCESS_FINE_LOCATION},
                    LOCATION_PERMISSION_REQUEST);
        }






        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("The Taxi On Road");
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }


}
