package com.example.home.token;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class amountpage extends AppCompatActivity {

    TextView t1,t2;
    Button b1,b2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amountpage);
        t1 = (TextView) findViewById(R.id.custnm);
        t2 = (TextView) findViewById(R.id.amtxt);
        b1 = (Button) findViewById(R.id.bt1);
        b2 = (Button) findViewById(R.id.bt2);
        Bundle bundle = getIntent().getExtras();
        final String s1 = bundle.getString("name");
        final String p = bundle.getString("position");
        final String s2 = bundle.getString("amount");
        t1.setText(s1);
        t2.setText(s2);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(amountpage.this, transferpage.class);
                intent.putExtra("amount", s2);
                intent.putExtra("position", p);
                intent.putExtra("name", s1);
                startActivity(intent);
                finish();
                ;
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(amountpage.this, transpage.class);
                intent.putExtra("name", s1);
                startActivity(intent);
            }
        });
    }
}
