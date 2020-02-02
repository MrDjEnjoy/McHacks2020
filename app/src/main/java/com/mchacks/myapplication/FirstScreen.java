package com.mchacks.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FirstScreen extends AppCompatActivity {

    private Button buyerButton;
    private Button merchantButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_screen);

        buyerButton = findViewById(R.id.custbut);

        merchantButton = findViewById(R.id.merchbut);

        buyerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        merchantButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toLogIn();
            }
        });
    }

    public void toLogIn(){
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }
}
