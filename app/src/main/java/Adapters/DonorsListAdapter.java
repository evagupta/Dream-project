package Adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.dreamapplication.DonateActivity;
import com.dreamapplication.ProposalInfoActivity;
import com.dreamapplication.R;

import java.util.List;

import Models.Project;

/**
 * Created by eva on 13/07/18.
 */

public class DonorsListAdapter extends BaseAdapter {


    private Context context;
    private List<Project> mProjects;

    public DonorsListAdapter(Context context, List<Project> projects) {
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
            convertView = inflater.inflate(R.layout.item_donor_listview, null, true);
            holder.txtTitle = (TextView) convertView.findViewById(R.id.txtTitle);
            holder.txtDonor = (TextView) convertView.findViewById(R.id.txtDonor);

            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }

        holder.txtTitle.setText("Project Name: "+mProjects.get(position).getProposalTitle());
        holder.txtDonor.setText("Number of Donors: "+mProjects.get(position).getNum_of_donors());

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
        public TextView txtTitle,txtDonor;
    }
}
