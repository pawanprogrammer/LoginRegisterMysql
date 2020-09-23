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

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
Button btn_login, btn_createaccount;
EditText etemail, etmobile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btn_login = findViewById(R.id.btn_login);
        btn_createaccount = findViewById(R.id.btn_createaccount);
        etemail = findViewById(R.id.etemail);
        etmobile = findViewById(R.id.etmobile);

        /*to change the actionbar titlename*/
        getSupportActionBar().setTitle("Login In App");

        btn_createaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent create = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(create);
                finish();
            }
        });

        /*to click on the login button*/
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*to add the empty form validation*/
                if (etemail.getText().toString().equals("")
                && etmobile.getText().toString().equals(""))
                {
                    Toast.makeText(LoginActivity.this,
                            "Please fill the form", Toast.LENGTH_SHORT).show();
                }
                else if (etemail.getText().toString().equals("")
                        || etmobile.getText().toString().equals("")
                )
                {
                    Toast.makeText(LoginActivity.this,
                            "Please fill the form", Toast.LENGTH_SHORT).show();
                }
                else if (etmobile.getText().toString().length()!=10)
                {
                    Toast.makeText(LoginActivity.this, "Mobile No not Valid", Toast.LENGTH_SHORT).show();
                }
                else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(etemail.getText().toString()).matches())
                {
                    Toast.makeText(LoginActivity.this, "Email Id not valid", Toast.LENGTH_SHORT).show();
                }
                else {
                    logintoServer();
                }
            }
        });

    }

    private void logintoServer() {
        StringRequest sr = new StringRequest(1, "http://searchkero.com/virusir/login.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        /*to check the email and mobile are exist or not from server*/
                        if (response.equalsIgnoreCase("Success"))
                        {
                            Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                            /*to clear the form*/
                            /*etmobile.setText("");
                            etemail.setText("");*/

                            /*to jump into MainActivity*/
                            Intent main = new Intent(LoginActivity.this, MainActivity.class);
                            main.putExtra("emailkey", etemail.getText().toString());
                            startActivity(main);
                            finish();
                        }
                        else {
                            Toast.makeText(LoginActivity.this, response, Toast.LENGTH_SHORT).show();

                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("emailid", etemail.getText().toString());
                map.put("mobileid", etmobile.getText().toString());
                return map;
            }
        };

        RequestQueue rq = Volley.newRequestQueue(LoginActivity.this);
        rq.add(sr);

    }
}