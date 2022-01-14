package com.Shawaz0149.pizzatipcalculatorapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class History extends AppCompatActivity {
    SharedPreferences pr;
    TextView amount;
    TextView tip;
    TextView total;
    TextView per;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        amount=findViewById(R.id.amount);
        tip=findViewById(R.id.tip);
        total=findViewById(R.id.total);
        per=findViewById(R.id.percent);
        pr = getApplicationContext().getSharedPreferences("PizzaTip", Context.MODE_PRIVATE);
        String amounts=pr.getString("amount","");
        String tips=pr.getString("tip","");
        String percent=pr.getString("percent","");
        String totals=pr.getString("total","");
        amount.setText(amounts);
        tip.setText(tips);
        total.setText(totals);
        per.setText(percent+"%");
    }
}
