package com.savitroday.savischools.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.savitroday.savischools.R;
import com.savitroday.savischools.api.response.Student;
import com.savitroday.savischools.databinding.StudentnameCellBinding;

import java.util.List;

/**
 * Created by owner on 10/12/2017.
 */

public class ActivityAllAdepter extends RecyclerView.Adapter<ActivityAllAdepter.viewHolder>

        {
        Activity activity;
        List<String> ldt;
        View view;
        AppCompatActivity apc;


public ActivityAllAdepter(Activity activity, List<String> ldt) {
        this.activity = activity;
        this.ldt = ldt;
        }


@Override
public ActivityAllAdepter.viewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_cell, parent, false);


        return new viewHolder(view);
        }

@Override
public void onBindViewHolder(ActivityAllAdepter.viewHolder holder, int position) {





        }


@Override
public int getItemCount() {
        return ldt.size();
        }

class viewHolder extends RecyclerView.ViewHolder {

    public viewHolder(View itemView) {

        super(itemView);

    }
}


}