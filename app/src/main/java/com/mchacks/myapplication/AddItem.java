package com.mchacks.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AddItem extends AppCompatActivity {

    private Merchant currMerchant;

    private EditText pname;
    private EditText price;
    private EditText tags;

    private Button confirm;
    private Button cancel;

    private RequestQueue MyRequestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        Intent i = getIntent();
        currMerchant = (Merchant) i.getSerializableExtra("currmerchant");
        MyRequestQueue = Volley.newRequestQueue(this);
        pname = findViewById(R.id.nameadditem);
        price = findViewById(R.id.priceadditem);
        tags = findViewById(R.id.tagsadditem);
        confirm = findViewById(R.id.confadditem);
        cancel = findViewById(R.id.cancadditem);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addItem();
                finish();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void addItem()
    {
        String stags = tags.getText().toString();
        final String[] splitTags = stags.split(",");

        final ArrayList<String> altags = new ArrayList<>();

        for(int i = 0; i < splitTags.length; i++)
        {
            altags.add(splitTags[i]);
        }

        Map<String, String> MyData = new HashMap<String, String>();
        MyData.put("name", pname.getText().toString()); //Add the data you'd like to send to the server.
        MyData.put("price", price.getText().toString());
        MyData.put("creator", currMerchant.getId());

        JSONArray tagarray = new JSONArray();
        tagarray.put(splitTags);

        JSONObject myJSON = new JSONObject(MyData);
        try {
            myJSON.accumulate("tags", tagarray);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        System.out.println(myJSON.toString());
        JsonObjectRequest MyStringRequest = new JsonObjectRequest(Request.Method.POST, getString(R.string.basic_url) + "/api/items", myJSON, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                try {
                    Item temp = new Item(pname.getText().toString(), altags, price.getText().toString(), response.getString("id"), currMerchant.getId());
                    currMerchant.getCatalog().add(temp);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                finish();
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
