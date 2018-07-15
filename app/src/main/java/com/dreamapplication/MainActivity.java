package com.dreamapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView txtprojects,txtdonate_money;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialization();
    }

    private void initialization(){

        txtprojects = (TextView) findViewById(R.id.txtprojects);
        txtdonate_money = (TextView) findViewById(R.id.txtdonate_money);

        txtprojects.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this,ListAllProjects.class);
                startActivity(intent);
            }
        });

        txtdonate_money.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_ = new Intent(MainActivity.this,DonorsListACtivity.class);
                startActivity(intent_);
            }
        });

    }


}
