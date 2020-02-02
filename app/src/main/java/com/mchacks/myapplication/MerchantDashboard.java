package com.mchacks.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MerchantDashboard extends AppCompatActivity {

    private Merchant currMerchant;
    private TextView name;
    private TextView address;
    private TextView description;
    private TextView email;
    private Button openCatalog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_merchant_dashboard);
        Intent i = getIntent();
        currMerchant = (Merchant) i.getSerializableExtra("merchant");
        name = findViewById(R.id.namedb);
        address = findViewById(R.id.addressdb);
        description = findViewById(R.id.descrpitiondb);
        email = findViewById(R.id.emaildb);
        openCatalog = findViewById(R.id.openCatalog);

        name.setText(currMerchant.getName());
        address.setText(currMerchant.getAddress());
        description.setText(currMerchant.getDescription());
        email.setText(currMerchant.getEmail());
        openCatalog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toCatalog();
            }
        });
    }

    private void toCatalog()
    {
        Intent intent = new Intent(this, SignUp.class);
        intent.putExtra("merchant2", currMerchant);
        startActivity(intent);
    }

}
