package com.mchacks.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONException;
import org.json.JSONObject;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

//    public void getSellersLocation(){
////        JsonObjectRequest MyStringRequest = new JsonObjectRequest(
////                Request.Method.GET,
////                getString(R.string.basic_url) + "/api/sellers",
////                new Response.Listener<JSONObject>() {
////
////
////                    @Override
////                    public void onResponse(JSONObject response) {
////                        try {
////                            Toast confirmation = Toast.makeText(getApplicationContext(), response.get("message").toString(), Toast.LENGTH_SHORT);
////                            confirmation.show();
////                        } catch (JSONException e) {
////                            e.printStackTrace();
////                        }
////                    }
////                }, new Response.ErrorListener() {
////
////            @Override
////            public void onErrorResponse(VolleyError error) {
////                // TODO: Handle error
////
////            }
////        });
////        MyRequestQueue.add(MyStringRequest);
////    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng marcheJeanTalon = new LatLng(45.5361095, -73.6170789);
        mMap.addMarker(new MarkerOptions().position(marcheJeanTalon).title("Marker in Marche Jean Talon"));

        LatLng marcheAtwater = new LatLng(45.4794197, -73.5852317);
        mMap.addMarker(new MarkerOptions().position(marcheAtwater).title("Marker in Marche Atwater"));

        LatLng marcheConcordia = new LatLng(45.4953688, -73.5867511);
        mMap.addMarker(new MarkerOptions().position(marcheConcordia).title("Marker in Marche Concordia"));

        LatLng trottier = new LatLng(45.5074102, -73.5811839);
        mMap.addMarker(new MarkerOptions().position(trottier).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)).title("You are here"));

        mMap.moveCamera(CameraUpdateFactory.newLatLng(trottier));
    }
}
