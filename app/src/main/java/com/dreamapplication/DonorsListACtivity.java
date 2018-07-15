package com.dreamapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.ProgressBar;
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

import Adapters.DonorsListAdapter;
import Adapters.ListProjectAdapter;
import Models.Project;

public class DonorsListACtivity extends AppCompatActivity {

    private ListView mDonorsListview;
    private ProgressBar progressbar;
    private List<Project> list;
    private DonorsListAdapter donorsListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donors_list_activity);
        initialization();
        getDonors();
    }


    private void initialization(){

        mDonorsListview = (ListView) findViewById(R.id.mDonorsListview);
        progressbar = (ProgressBar) findViewById(R.id.progressbar);
    }

    private void getDonors(){

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

                            Log.e("response",response);


                            JSONArray jsonArray_proposals = jsonObject.getJSONArray("proposals");

                            for (int m = 0;m<jsonArray_proposals.length();m++) {

                                Project model = new Project();

                                JSONObject object = jsonArray_proposals.getJSONObject(m);
                                model.setProposalId(object.getString("id"));
                                model.setProposalTitle(object.getString("title"));
                                model.setNum_of_donors(object.getString("numDonors"));

                                list.add(model);
                            }
                            if (list.size() == 0){
                                Toast.makeText(DonorsListACtivity.this,R.string.no_data, Toast.LENGTH_SHORT).show();
                            }
                            else {
                                donorsListAdapter = new DonorsListAdapter(DonorsListACtivity.this,list);
                                mDonorsListview.setAdapter(donorsListAdapter);
                            }
                        }

                        catch (JSONException e){
                            progressbar.setVisibility(View.GONE);
                            Toast.makeText(DonorsListACtivity.this,""+e.toString(),Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressbar.setVisibility(View.GONE);
                        Toast.makeText(DonorsListACtivity.this,""+error.toString(),Toast.LENGTH_SHORT).show();

                    }
                });
        req.setRetryPolicy(new DefaultRetryPolicy(10000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Volley.newRequestQueue(this).add(req);


    }

}
