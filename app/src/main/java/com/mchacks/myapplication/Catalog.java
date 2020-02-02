package com.mchacks.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class Catalog extends AppCompatActivity {

    private Merchant currMerchant;
    private LinearLayout lin;
    private Button aItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);
        Intent i = getIntent();
        currMerchant = (Merchant) i.getSerializableExtra("merchant2");
        lin = findViewById(R.id.linlayout);
        aItem = findViewById(R.id.addItem);

        aItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addItem();
            }
        });
        loadCatalog();
    }

    private void loadCatalog()
    {
        for (Item i: currMerchant.getCatalog()) {
            LinearLayout tempLayout = new LinearLayout(this);
            tempLayout.setOrientation(LinearLayout.HORIZONTAL);
            tempLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            TextView tempName = new TextView(this);
            tempName.setText(i.getName());
            TextView tempPrice = new TextView(this);
            tempPrice.setText(i.getPrice());
            StringBuilder tags = new StringBuilder();
            for(int j = 0; j < i.getTags().size(); j++)
            {
                tags.append(i.getTags().get(j) + "\n");
            }
            TextView tempTags = new TextView(this);
            tempTags.setText(tags.toString());
            tempLayout.addView(tempName);
            tempLayout.addView(tempPrice);
            tempLayout.addView(tempTags);
            lin.addView(tempLayout);
        }
    }

    private void addItem()
    {
        Intent intent = new Intent(getApplicationContext(), AddItem.class);
        intent.putExtra("currmerchant", currMerchant);
        startActivity(intent);
    }
}
