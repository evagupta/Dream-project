package Adapters;

import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.dreamapplication.DonateActivity;
import com.dreamapplication.DonorsListACtivity;
import com.dreamapplication.ProposalInfoActivity;
import com.dreamapplication.R;

import java.util.List;

import Models.Project;

/**
 * Created by eva on 13/07/18.
 */

public class ListProjectAdapter extends BaseAdapter {

    private Context context;
    private List<Project> mProjects;

    public ListProjectAdapter(Context context, List<Project> projects) {
        this.context = context;
        this.mProjects = projects;
    }

    @Override
    public int getCount() {
        return mProjects.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final Holder holder;

        if (convertView == null) {
            holder = new Holder();

            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_list, null, true);
            holder.txtTitle = (TextView) convertView.findViewById(R.id.projectTitle);
            holder.projectExpiration = (TextView) convertView.findViewById(R.id.projectExpiration);
            holder.projectFundingStatus = (TextView) convertView.findViewById(R.id.projectFundingStatus);
            holder.btn_donate = (Button) convertView.findViewById(R.id.btn_donate);


            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }

        holder.txtTitle.setText(mProjects.get(position).getProposalTitle());
        holder.projectFundingStatus.setText("Funding Status: "+mProjects.get(position).getProjectFunds());
        holder.projectExpiration.setText("Project Proposal Expires on: "+mProjects.get(position).getProjectExpiration());

        if (mProjects.get(position).getProjectFunds().equals("needs funding")){
            holder.btn_donate.setVisibility(View.VISIBLE);
            holder.btn_donate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent_ = new Intent(context, DonateActivity.class);
                    intent_.putExtra("id",mProjects.get(position).getProposalId());
                    intent_.putExtra("title",mProjects.get(position).getProposalTitle());
                    context.startActivity(intent_);
                }
            });
        }

        Log.e("GET<><>",mProjects.get(position).getProjectExpiration()+"      "+mProjects.get(position).getProjectFunds());

        holder.txtTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ProposalInfoActivity.class);
                intent.putExtra("id",mProjects.get(position).getProposalId());
                intent.putExtra("title",mProjects.get(position).getProposalTitle());
                intent.putExtra("description",mProjects.get(position).getProposalDescription());
                intent.putExtra("url",mProjects.get(position).getProposalUrl());
                context.startActivity(intent);
            }
        });
        return convertView;
    }

    @Override
    public int getItemViewType(int position) {

        return position;
    }

    @Override
    public int getViewTypeCount() {
        return getCount();
    }

    private class Holder {
        public TextView txtTitle,projectFundingStatus,projectExpiration;
        public Button btn_donate;
    }
}