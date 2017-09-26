package com.savitroday.savischools.adapter;
import android.app.Activity;

import android.support.v7.app.AppCompatActivity;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.savitroday.savischools.R;
import com.savitroday.savischools.api.response.Invoice;
import com.savitroday.savischools.api.response.Student;
import com.squareup.picasso.Picasso;

import java.util.List;
/**
 * Created by owner on 9/26/2017.
 */

public class PopupRecyclerviewAdepter extends RecyclerView.Adapter<PopupRecyclerviewAdepter.viewHolder>

{
    Activity activity;
    List<Student> ldt;
    View view;
    AppCompatActivity apc;


    public PopupRecyclerviewAdepter(Activity activity, List<Student> ldt) {
        this.activity = activity;
        this.ldt = ldt;
    }


    @Override
    public PopupRecyclerviewAdepter.viewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.studentlist, parent, false);


        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(PopupRecyclerviewAdepter.viewHolder holder, int position) {
        final Student student = ldt.get(position);

    //  Picasso.with(activity).load().into(holder.stImage);
      holder.stName.setText(student.studentName);

    }


    @Override
    public int getItemCount() {
        return ldt.size();
    }

    class viewHolder extends RecyclerView.ViewHolder {

ImageView stImage;
        TextView stName;
        public viewHolder(View itemView) {

            super(itemView);
            stImage=itemView.findViewById(R.id.stImage);
            stName=itemView.findViewById(R.id.stname);

        }
    }


}