package com.trishasofttech.loginregistermysql;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ArrayListActivity extends AppCompatActivity {
RecyclerView recyclerView;
List<Data> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_array_list);
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        loadData();
       // CustomAdapter customAdapter = new CustomAdapter();
    }

    private void loadData() {
        StringRequest sr = new StringRequest(0, "http://searchkero.com/virusir/userinfoshow.php",
                new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                /*display data into etname after jsonparsing*/
                try {
                    JSONObject jo = new JSONObject(response);
                    //etname.setText(jo.getJSONArray("Server_Data") +"");
                    JSONArray ja = jo.getJSONArray("Server_Data");
                    for (int i =0; i< ja.length(); i++)
                    {
                        JSONObject jo1 = ja.getJSONObject(i);
                        list.add(new Data(jo1.getString("name"), jo1.getString("email"), jo1.getString("mobile")));
                    }
                    CustomAdapter customAdapter = new CustomAdapter();
                    recyclerView.setAdapter(customAdapter);
                }
                catch (JSONException e)
                {

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue rq = Volley.newRequestQueue(ArrayListActivity.this);
        rq.add(sr);
    }

    private class CustomAdapter extends RecyclerView.Adapter<CustomHolder> {
        @NonNull
        @Override
        public CustomHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
            CustomHolder customHolder = new CustomHolder(v);
            return customHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull CustomHolder holder, int position) {
            Data data = list.get(position);
            holder.tv_name.setText(data.getName());
            holder.tv_mobile.setText(data.getMobile());
            holder.tv_email.setText(data.getEmail());
        }

        @Override
        public int getItemCount() {
            return list.size();
        }
    }

    private class CustomHolder extends RecyclerView.ViewHolder {
        TextView tv_name,tv_email, tv_mobile;
        public CustomHolder(@NonNull View itemView) {
            super(itemView);
            tv_email = itemView.findViewById(R.id.tv_email);
            tv_mobile = itemView.findViewById(R.id.tv_mobile);
            tv_name = itemView.findViewById(R.id.tv_name);
        }
    }
}