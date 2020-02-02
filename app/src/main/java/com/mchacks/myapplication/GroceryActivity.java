package com.mchacks.myapplication;

import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class GroceryActivity extends AppCompatActivity {
    private FusedLocationProviderClient fusedLocationProviderClient;
    private RequestQueue MyRequestQueue;
    private ArrayList<String> shoppingList;
    private ArrayAdapter<String> mAdapter;
    private ListView listView;
    private EditText mEditText;
    private Button mSaveButton;
    private Button mShopButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grocery);

        listView = findViewById(R.id.grocery_list);
        mSaveButton = findViewById(R.id.button_save);
        mShopButton = findViewById(R.id.shop_button);
        mEditText = findViewById(R.id.edittext_grocery);

        shoppingList = new ArrayList<>();
        mAdapter = new ArrayAdapter<String>(getApplicationContext(),
                R.layout.grocery_item, R.id.task_title, shoppingList);
        mSaveButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String name = mEditText.getText().toString();
                shoppingList.add(name);
                listView.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();
            }
        });

        mShopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GroceryActivity.this, MapsActivity.class);
                startActivity(intent);
            }
        });
//        TextView textView = new TextView(this);
    }

    private void shop() {
        final double[] locationArr = new double[2];
        fusedLocationProviderClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {

                        if (location != null) {
                            locationArr[0] = location.getLongitude();
                            locationArr[1] = location.getLatitude();
                        } else {
                            try {
                                Toast confirmation = Toast.makeText(getApplicationContext(), "Turn on Location on your device", Toast.LENGTH_SHORT);
                                confirmation.show();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });

        JSONArray jsonList = new JSONArray(shoppingList);
        JSONObject myJSON = new JSONObject();
        try {
            myJSON.put("tags", jsonList);
            myJSON.put("lon", locationArr[0]);
            myJSON.put("lat", locationArr[1]);
        } catch (JSONException err) {
            //TODO: Add an error
        }
        System.out.println(myJSON.toString());
        JsonObjectRequest MyStringRequest = new JsonObjectRequest(
                Request.Method.GET,
                getString(R.string.basic_url) + "/api/sellers",
                new Response.Listener<JSONObject>() {


                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Toast confirmation = Toast.makeText(getApplicationContext(), response.get("message").toString(), Toast.LENGTH_SHORT);
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

    private TextView createNewTextView(String text) {
        final LinearLayout.LayoutParams lparams =
                new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        final TextView textView = new TextView(this);
        textView.setLayoutParams(lparams);
        textView.setText(text);
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(20);
        return textView;
    }

}


