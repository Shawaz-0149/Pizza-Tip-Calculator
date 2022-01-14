package com.Shawaz0149.pizzatipcalculatorapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.hsalf.smilerating.SmileRating;

public class MainActivity extends AppCompatActivity {
    TextView show;
    Button history,save;
    EditText amount;
    TextView tip;
    TextView total;
    SmileRating sr;
    SharedPreferences pr;
    SharedPreferences.Editor edi;
    int percent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        show = findViewById(R.id.seekper);
        history=findViewById(R.id.history);
        sr = findViewById(R.id.sr);
        amount = findViewById(R.id.amount);
        tip = findViewById(R.id.tip);
        total = findViewById(R.id.total);
        save=findViewById(R.id.save);

        pr=getSharedPreferences("PizzaTip", Context.MODE_PRIVATE);
        edi=pr.edit();

        save.setOnClickListener(v -> {
            String amounts=amount.getText().toString();
             edi.putString("amount",amounts);
            String per= String.valueOf(percent);
            edi.putString("percent",per);
            String tip1=tip.getText().toString();
            edi.putString("tip",tip1);
            String total1=total.getText().toString();
            edi.putString("total",total1);
             edi.commit();
            Toast.makeText(MainActivity.this, "Saved", Toast.LENGTH_SHORT).show();
        });
        history.setOnClickListener(v -> {
            if(pr.getString("amount","").equals(""))
            {
                Toast.makeText(getApplicationContext(), "Nothing Save", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Intent intent=new Intent(MainActivity.this,History.class);
                startActivity(intent);
            }
        });
        sr.setOnSmileySelectionListener((smiley, reselected) -> {
            switch (smiley) {
                case SmileRating.BAD:
                    percent=20;
                    Toast.makeText(getApplicationContext(), "BAD", Toast.LENGTH_SHORT).show();
                    break;
                case SmileRating.GOOD:
                    percent=40;
                    Toast.makeText(getApplicationContext(), "GOOD", Toast.LENGTH_SHORT).show();
                    break;
                    case SmileRating.GREAT:
                        percent=50;
                    Toast.makeText(getApplicationContext(), "GREAT", Toast.LENGTH_SHORT).show();
                    break;
                case SmileRating.OKAY:
                    percent=30;
                    Toast.makeText(getApplicationContext(), "OKAY", Toast.LENGTH_SHORT).show();
                    break;
                case SmileRating.TERRIBLE:
                    percent=10;
                    Toast.makeText(getApplicationContext(), "TERRIBLE", Toast.LENGTH_SHORT).show();
                    break;
            }
            show.setText(String.valueOf(percent));
            calculate(percent);
        });
        amount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                calculate(percent);
            }
        });

    }
    private void calculate(int percent)
    {
        if(amount.length()==0)
        {
            amount.requestFocus();
            amount.setError("Enter Amount");
        }
        else
        {
            double a=Double.parseDouble(amount.getText().toString());
            double ti=(a*percent)/100.0;
            double tot=ti+a;
            tip.setText(String.valueOf(ti));
            total.setText(String.valueOf(tot));



        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        if(id==R.id.save)
        {
            if(pr.getString("amount","").equals(""))
            {
                Toast.makeText(getApplicationContext(), "Nothing Save", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Intent intent=new Intent(MainActivity.this,History.class);
                startActivity(intent);
            }
        }
        return super.onOptionsItemSelected(item);
    }
}