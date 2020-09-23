package com.trishasofttech.loginregistermysql;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView_userinfo;
    Button btnSubmit;
    EditText etname,etemail,etmobile;

    public static String nameArr [];
    public static String emailArr [];
    public static String mobileArr [];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etname = findViewById(R.id.etname);
        etemail = findViewById(R.id.etemail);
        etmobile = findViewById(R.id.etmobile);
        btnSubmit = findViewById(R.id.btnSubmit);

        /*to receive the intent data*/
        Intent data = getIntent();
        /*display email id*/
        etemail.setText(data.getStringExtra("emailkey"));

        recyclerView_userinfo = findViewById(R.id.recyclerview_userinfo);
        /*to set the fixed recyclerview size*/
        recyclerView_userinfo.setHasFixedSize(true);
        /*recyclerview design in linear form*/
        recyclerView_userinfo.setLayoutManager(new LinearLayoutManager(this));



        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                StringRequest sr = new StringRequest(1, "http://searchkero.com/virusir/userinfo.php",
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                /*to clear the form*/
                                etemail.setText("");
                                etmobile.setText("");
                                etname.setText("");

                                Snackbar.make(view, response, 5000).show();

                                datafetchfromserver();
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> map = new HashMap<>();
                        map.put("namekey", etname.getText().toString());
                        map.put("emailkey",etemail.getText().toString());
                        map.put("mobilekey", etmobile.getText().toString());
                        return map;
                    }
                };
                RequestQueue rq = Volley.newRequestQueue(MainActivity.this);
                rq.add(sr);
            }
        });

    }

    private void datafetchfromserver() {
        StringRequest sr = new StringRequest(0, "http://searchkero.com/virusir/userinfoshow.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        /*display data into etname after jsonparsing*/
                        try {
                            JSONObject jo = new JSONObject(response);
                            //etname.setText(jo.getJSONArray("Server_Data") +"");

                            JSONArray ja = jo.getJSONArray("Server_Data");
                            nameArr = new String[ja.length()];
                            emailArr = new String[ja.length()];
                            mobileArr = new String[ja.length()];
                            /*iteration for fetching all data from array*/
                            /*to get all entry/data from api*/
                            for (int i=0; i<ja.length(); i++)
                            {
                                JSONObject jo1 = ja.getJSONObject(i);
                                nameArr[i] = jo1.getString("name");
                                emailArr[i] = jo1.getString("email");
                                mobileArr[i] = jo1.getString("mobile");
                            }

                            /*to call the Adapter for recyclerview after click on the button*/
                            MyAdapter myAdapter  = new MyAdapter(MainActivity.this, nameArr, emailArr, mobileArr);
                            /*to attach the adapter with recyclerview*/
                            recyclerView_userinfo.setAdapter(myAdapter);

                            /*for single entry*/
                            /*JSONObject jo1 = ja.getJSONObject(5);
                            String name = jo1.getString("name");
                            String email = jo1.getString("email");
                            String mobile = jo1.getString("mobile");
                            btnSubmit.setText(name + "\n"+ email +"\n"+ mobile);*/
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue rq = Volley.newRequestQueue(MainActivity.this);
        rq.add(sr);
    }
}