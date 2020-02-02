package com.mchacks.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Console;
import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {

    private RequestQueue MyRequestQueue;

    private EditText email;
    private EditText pw;
    private Button lIB;
    private TextView test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        MyRequestQueue = Volley.newRequestQueue(this);
        email = findViewById(R.id.email);
        pw = findViewById(R.id.password);
        lIB = findViewById(R.id.login);
        test = findViewById(R.id.editText4);

        lIB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                login();
            }
        });
    }

    public void login() {
        Map<String, String> MyData = new HashMap<String, String>();
        MyData.put("email", email.getText().toString()); //Add the data you'd like to send to the server.
        MyData.put("password", pw.getText().toString());

        JSONObject myJSON = new JSONObject(MyData);
        System.out.println(myJSON.toString());
        JsonObjectRequest MyStringRequest = new JsonObjectRequest(Request.Method.POST, getString(R.string.basic_url) + "/api/sellers/login", myJSON, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                try {
                    Toast confirmation = Toast.makeText(getApplicationContext(),response.get("message").toString(), Toast.LENGTH_SHORT);
                    confirmation.show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO: Handle error

            }
        });
        MyRequestQueue.add(MyStringRequest);
    }
}
