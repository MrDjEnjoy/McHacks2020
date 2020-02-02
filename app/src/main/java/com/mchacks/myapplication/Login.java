package com.mchacks.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {

    private RequestQueue MyRequestQueue;

    private EditText email;
    private EditText pw;
    private Button lIB;
    private TextView test;
    private Button toSignUP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        MyRequestQueue = Volley.newRequestQueue(this);
        email = findViewById(R.id.email);
        pw = findViewById(R.id.password);
        lIB = findViewById(R.id.login);
        test = findViewById(R.id.suaddress);
        toSignUP = findViewById(R.id.toSignup);

        lIB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                login();
            }
        });

        toSignUP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToSignUp();
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
                    Merchant temp = new Merchant(response.getString("name"), response.getString("location"), response.getString("id"), response.getString("email"));
                    ArrayList<Item> items = getItems(response.getJSONArray("catalog"));
                    ArrayList<String> sustags = new ArrayList<>();
                    JSONArray sustaglist = response.getJSONArray("sustain");
                    for(int i = 0; i < sustaglist.length(); i++)
                    {
                        sustags.add(sustaglist.getString(i));
                    }
                    temp.setCatalog(items);
                    temp.setDescription(response.getString("description"));
                    temp.setSustainibility(sustags);
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

    public ArrayList<Item> getItems(JSONArray itemList) throws JSONException {
        final ArrayList<Item> grocerList = new ArrayList<>();
        for (int i = 0; i < itemList.length(); i++)
        {
            JsonObjectRequest MyStringRequest = new JsonObjectRequest(Request.Method.GET, getString(R.string.basic_url) + "/api/items/" + itemList.getString(i), new JSONObject(), new Response.Listener<JSONObject>() {

                @Override
                public void onResponse(JSONObject response) {
                    try {
                        Toast confirmation = Toast.makeText(getApplicationContext(),response.get("message").toString(), Toast.LENGTH_SHORT);
                        confirmation.show();
                        ArrayList<String> tags = new ArrayList<>();
                        JSONArray jTags = response.getJSONArray("tags");
                        for(int j = 0; j < jTags.length(); j++) tags.add(jTags.getString(j));
                        Item temp = new Item(response.getString("name"), tags, response.getString("price"), response.getString("_id"), response.getString("creator"));
                        grocerList.add(temp);
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
        return grocerList;
    }

    public void goToSignUp()
    {
        Intent intent = new Intent(this, SignUp.class);
        startActivity(intent);
    }
}
