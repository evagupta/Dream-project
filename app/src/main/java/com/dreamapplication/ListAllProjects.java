package com.dreamapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import Adapters.ListProjectAdapter;
import Models.Project;

public class ListAllProjects extends AppCompatActivity {

    private EditText et_search;
    private ListView mProjectListview;
    private ProgressBar progressbar;
    private List<Project> list;
    String searchTerm,searchUrl,totalproposals;
    private TextView txtsearchTerm,txtsearchURL,txttotalprop;
    private ListProjectAdapter adapter;
    private Button searchBtn;
    String et_value;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_all_projects);
        initialization();

        getProposals();
    }


    private void initialization(){
        et_search = (EditText) findViewById(R.id.et_search);
        mProjectListview = (ListView) findViewById(R.id.mProjectListview);
        progressbar = (ProgressBar) findViewById(R.id.progressbar);
        txttotalprop = (TextView) findViewById(R.id.txttotalprop);
        txtsearchTerm = (TextView) findViewById(R.id.txtsearchTerm);
        txtsearchURL = (TextView) findViewById(R.id.txtsearchURL);
        searchBtn = (Button) findViewById(R.id.searchBtn);
        progressbar.setVisibility(View.VISIBLE);

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (et_search.getText().toString().equals("") || et_search.getText().toString() == null){
                    Toast.makeText(ListAllProjects.this,"Enter the value",Toast.LENGTH_SHORT).show();
                }else {
                    et_value = et_search.getText().toString();
                    searchedData();
                }
            }
        });

    }

    private void getProposals(){

        String url = "https://api.donorschoose.org/common/json_feed.html?APIKey=DONORSCHOOSE";

        Log.e("URL<><>",url);

        StringRequest req = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        list = new ArrayList<Project>();
                        progressbar.setVisibility(View.GONE);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            searchTerm = jsonObject.getString("searchTerms");
                            searchUrl = jsonObject.getString("searchURL");
                            totalproposals = jsonObject.getString("totalProposals");

                            if (!searchTerm.equals("")){
                                txtsearchTerm.setText("Search Term: "+searchTerm);
                            }
                            if (!searchUrl.equals("")){
                                txtsearchURL.setText("SearchURL: "+searchUrl);
                            }
                            if (!totalproposals.equals("")){
                                txttotalprop.setText("Total Proposals: "+totalproposals);
                            }

                            Log.e("searchTerm",searchTerm);
                            Log.e("response",response);


                            JSONArray jsonArray_proposals = jsonObject.getJSONArray("proposals");

                            for (int m = 0;m<jsonArray_proposals.length();m++) {

                                Project model = new Project();

                                JSONObject object = jsonArray_proposals.getJSONObject(m);
                                model.setProposalId(object.getString("id"));
                                model.setProposalTitle(object.getString("title"));
                                model.setProposalDescription(object.getString("shortDescription"));
                                model.setProposalUrl(object.getString("proposalURL"));
                                model.setProjectExpiration(object.getString("expirationDate"));
                                Log.e("EXPIRATION",object.getString("expirationDate"));
                                model.setProjectFunds(object.getString("fundingStatus"));
                                list.add(model);
                            }
                            if (list.size() == 0){
                                Toast.makeText(ListAllProjects.this,R.string.no_data, Toast.LENGTH_SHORT).show();
                            }
                            else {
                                adapter = new ListProjectAdapter(ListAllProjects.this,list);
                                mProjectListview.setAdapter(adapter);
                            }
                        }

                        catch (JSONException e){
                            progressbar.setVisibility(View.GONE);
                            Toast.makeText(ListAllProjects.this,""+e.toString(),Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressbar.setVisibility(View.GONE);
                        Toast.makeText(ListAllProjects.this,""+error.toString(),Toast.LENGTH_SHORT).show();

                    }
                });
        req.setRetryPolicy(new DefaultRetryPolicy(10000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Volley.newRequestQueue(this).add(req);


    }

    private void searchedData(){

        String url = "https://api.donorschoose.org/common/json_feed.html?APIKey=DONORSCHOOSE&keywords="+et_value;

        Log.e("SearchURL",url);

        StringRequest req = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        list = new ArrayList<Project>();
                        progressbar.setVisibility(View.GONE);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            searchTerm = jsonObject.getString("searchTerms");
                            searchUrl = jsonObject.getString("searchURL");
                            totalproposals = jsonObject.getString("totalProposals");

                            if (!searchTerm.equals("")){
                                txtsearchTerm.setText("Search Term: "+searchTerm);
                            }
                            if (!searchUrl.equals("")){
                                txtsearchURL.setText("SearchURL: "+searchUrl);
                            }
                            if (!totalproposals.equals("")){
                                txttotalprop.setText("Total Proposals: "+totalproposals);
                            }

                            Log.e("searchTerm",searchTerm);
                            Log.e("response",response);


                            JSONArray jsonArray_proposals = jsonObject.getJSONArray("proposals");

                            for (int m = 0;m<jsonArray_proposals.length();m++) {

                                Project model = new Project();

                                JSONObject object = jsonArray_proposals.getJSONObject(m);
                                model.setProposalId(object.getString("id"));
                                model.setProposalTitle(object.getString("title"));
                                model.setProposalDescription(object.getString("shortDescription"));
                                model.setProposalUrl(object.getString("proposalURL"));
                                model.setProjectExpiration(object.getString("expirationDate"));
                                Log.e("EXPIRATION",object.getString("expirationDate"));
                                model.setProjectFunds(object.getString("fundingStatus"));
                                list.add(model);
                            }
                            if (list.size() == 0){
                                Toast.makeText(ListAllProjects.this,R.string.no_data, Toast.LENGTH_SHORT).show();
                            }
                            else {
                                adapter = new ListProjectAdapter(ListAllProjects.this,list);
                                mProjectListview.setAdapter(adapter);
                            }
                        }

                        catch (JSONException e){
                            progressbar.setVisibility(View.GONE);
                            Toast.makeText(ListAllProjects.this,""+e.toString(),Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressbar.setVisibility(View.GONE);
                        Toast.makeText(ListAllProjects.this,""+error.toString(),Toast.LENGTH_SHORT).show();

                    }
                });
        req.setRetryPolicy(new DefaultRetryPolicy(10000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Volley.newRequestQueue(this).add(req);

    }
}
