package com.example.final_finaaaal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
public class Search_rec extends AppCompatActivity {
    private RecyclerView recycler;
    private final String BASE_URL = "http://10.0.2.2/fnn/c.php";
    private ArrayList<Room> items = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_rec);
        recycler=(RecyclerView) findViewById(R.id.searchrecycler);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        loadItems();

    }
    private void loadItems() {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, BASE_URL,
                response -> {



                    try {

                        JSONArray array = new JSONArray(response);
                        System.err.println(array);
                        for (int i = 0; i<array.length(); i++){
                            System.err.println("fnn");
                            JSONObject object = array.getJSONObject(i);



                            System.err.println("the status is "+object.getInt("status"));
                            boolean statusBoolean=false;
                            if(object.getInt("status")==1){
                                statusBoolean=true;

                            }else if(object.getInt("status")==0){
                                statusBoolean=false;

                            }



                            if (!statusBoolean){

                                Room pizza=new Room(object.getInt("room_id"),statusBoolean,
                                        object.getString("description"),
                                        object.getInt("floor"),
                                        object.getString("room_photo"),
                                        object.getString("room_type"),
                                        object.getDouble("price"));
                                items.add(pizza);
                            }


                           /*Room room= new Room(object.getString("room_type"),object.getString("description"),
                                    object.getInt("room_photo"), object.getInt("room_id"),object.getInt("status"),
                            object.getInt("floor"));*/


                        }

                    }catch (Exception e){

                    }
                    CaptionImagesAdapter adapter = new CaptionImagesAdapter(items,Search_rec.this);
                    recycler.setAdapter(adapter);
                    Log.d("d",items.toString());
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error)
            {
                Toast.makeText(Search_rec.this, error.toString(),Toast.LENGTH_LONG).show();
            }
        });

        Volley.newRequestQueue(Search_rec.this).add(stringRequest);

    }

}
