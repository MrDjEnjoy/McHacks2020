package com.mchacks.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.os.Bundle;
import android.widget.EditText;
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

import java.util.HashMap;
import java.util.Map;

public class SignUp extends AppCompatActivity {

    private EditText email;
    private EditText pw;
    private EditText name;
    private EditText address;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        email = findViewById(R.id.email);
        pw = findViewById(R.id.password);
        name = findViewById(R.id.email);
        address = findViewById(R.id.email);
        MyRequestQueue = Volley.newRequestQueue(this);
    }

    RequestQueue MyRequestQueue;

    public void signup() {
        Map<String, String> MyData = new HashMap<String, String>();
        MyData.put("email", email.getText().toString()); //Add the data you'd like to send to the server.
        MyData.put("password", pw.getText().toString());
        MyData.put("name", name.getText().toString());
        MyData.put("location", address.getText().toString());

        JSONObject myJSON = new JSONObject(MyData);
        System.out.println(myJSON.toString());
        JsonObjectRequest MyStringRequest = new JsonObjectRequest(Request.Method.POST, getString(R.string.basic_url) + "/api/sellers/signup", myJSON, new Response.Listener<JSONObject>() {

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
