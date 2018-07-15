package com.dreamapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

public class ProposalInfoActivity extends AppCompatActivity {


    private TextView txtUrl,txtTitle,txtDescription;
    String id,url,title,description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proposal_info);
        initialization();
    }

    private void initialization(){
        txtUrl = (TextView) findViewById(R.id.txtUrl);
        txtTitle = (TextView) findViewById(R.id.txtTitle);
        txtDescription = (TextView) findViewById(R.id.txtDescription);

         id = getIntent().getStringExtra("id");
         url = getIntent().getStringExtra("url");
         title = getIntent().getStringExtra("title");
         description = getIntent().getStringExtra("description");

         txtUrl.setText("URL: "+url);
         txtTitle.setText("ID: "+id +"\nTITLE: "+title);
         txtDescription.setText("Short Description: "+description);

         txtUrl.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {

                 Intent intent = new Intent(ProposalInfoActivity.this, WebViewActivity.class);
                 intent.putExtra("url",url);
                 startActivity(intent);
                 finish();
             }
         });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
