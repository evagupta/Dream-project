package com.dreamapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class DonateActivity extends AppCompatActivity {

    private TextView txtId,txtTitle;
    private EditText et_amount,et_donarAmount;
    private Button btn_donate;

    String id,title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donate);
        initialization();

    }

    private void initialization(){
        txtId = (TextView) findViewById(R.id.txtId);
        txtTitle = (TextView) findViewById(R.id.txtTitle);
        et_amount = (EditText) findViewById(R.id.et_amount);
        et_donarAmount = (EditText) findViewById(R.id.et_donarAmount);
        btn_donate = (Button) findViewById(R.id.btn_donate);

        id = getIntent().getStringExtra("id");
        title = getIntent().getStringExtra("title");

        Log.e("LOGSSSS<><>",id +" and "+title);

        txtId.setText("Id: "+id);
        txtTitle.setText("Title: "+title);

        btn_donate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (et_amount.getText().toString().equals("") || et_donarAmount.getText().toString().equals("")){
                    Toast.makeText(DonateActivity.this,R.string.toast_error,Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(DonateActivity.this,R.string.toast,Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
