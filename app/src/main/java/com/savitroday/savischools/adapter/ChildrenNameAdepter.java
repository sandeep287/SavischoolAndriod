package com.savitroday.savischools.adapter;

import android.app.Activity;
import android.databinding.DataBindingUtil;
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
 * Created by owner on 10/10/2017.
 */

public class ChildrenNameAdepter extends RecyclerView.Adapter<ChildrenNameAdepter.viewHolder>

        {
        Activity activity;
        List<Student> ldt;

        AppCompatActivity apc;
            StudentnameCellBinding mBindings;

public ChildrenNameAdepter(Activity activity, List<Student> ldt) {
        this.activity = activity;
        this.ldt = ldt;
        }


@Override
public ChildrenNameAdepter.viewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        mBindings = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.studentname_cell, parent, false);


        return new viewHolder(mBindings.getRoot());
        }

@Override
public void onBindViewHolder(ChildrenNameAdepter.viewHolder holder, int position) {
    mBindings.setStudent(ldt.get(position));

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