package com.mchacks.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

    private Button signUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        email = findViewById(R.id.suemail);
        pw = findViewById(R.id.supw);
        name = findViewById(R.id.subus);
        address = findViewById(R.id.suaddress);
        signUp = findViewById(R.id.signupbut);
        MyRequestQueue = Volley.newRequestQueue(this);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
            }
        });
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
                        Merchant temp = new Merchant(response.getString("name"),  response.getString("location"), response.getString("id"), response.getString("email"));
                    Intent intent = new Intent(getApplicationContext(), MerchantDashboard.class);
                    intent.putExtra("merchant", temp);
                    startActivity(intent);
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
