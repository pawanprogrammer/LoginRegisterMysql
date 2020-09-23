package com.trishasofttech.loginregistermysql;

import androidx.appcompat.app.AppCompatActivity;

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

import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {
EditText etname,etmobile,etemail;
Button btnregister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        btnregister = findViewById(R.id.btnregister);
        etemail = findViewById(R.id.etemail);
        etmobile = findViewById(R.id.etmobile);
        etname = findViewById(R.id.etname);

        /*to change the actionbar titlename*/
        getSupportActionBar().setTitle("Register In App");

        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringRequest sr = new StringRequest(1, "http://searchkero.com/virusir/register.php", new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(RegisterActivity.this, response, Toast.LENGTH_LONG).show();
                        /*to clear the form*/
                        etemail.setText("");
                        etmobile.setText("");
                        etname.setText("");
                        /*get server response*/
                        Intent register = new Intent(RegisterActivity.this, LoginActivity.class);
                        startActivity(register);
                        finish();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        /*get the error*/
                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> map = new HashMap<>();
                        map.put("namekey", etname.getText().toString());
                        map.put("emailkey", etemail.getText().toString());
                        map.put("mobilekey", etmobile.getText().toString());
                        return map;
                    }
                };

                RequestQueue rq = Volley.newRequestQueue(RegisterActivity.this);
                rq.add(sr);
            }
        });

    }
}