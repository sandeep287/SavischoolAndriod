package com.savitroday.savischools.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.savitroday.savischools.R;
import com.savitroday.savischools.api.response.Student;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by owner on 9/26/2017.
 */

public class StudentListAdapter extends RecyclerView.Adapter<StudentListAdapter.viewHolder> {
    Context _context;
    List<Student> ldt;
    View view;
    
    public StudentListAdapter(Context _context, List<Student> ldt) {
        this._context = _context;
        this.ldt = ldt;
    }
    
    
    @Override
    public StudentListAdapter.viewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.studentlist, parent, false);
        return new viewHolder(view);
    }
    
    @Override
    public void onBindViewHolder(StudentListAdapter.viewHolder holder, int position) {
        final Student student = ldt.get(position);
        if (student.iconMediaPath != null)
            Picasso.with(_context)
                    .load(student.iconMediaPath)
                    .placeholder(R.drawable.profile_img)
                    .into(holder.stImage);
        holder.stName.setText(student.studentName);
        if(student.isdefault){
            holder.stImage.setBorderColor(ContextCompat.getColor(_context,R.color.white));
        }
        else {
            holder.stImage.setBorderColor(ContextCompat.getColor(_context,android.R.color.transparent));
        }
    }
    
    
    @Override
    public int getItemCount() {
        return ldt.size();
    }
    
    class viewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    
        CircleImageView stImage;
        TextView stName;
        
        public viewHolder(View itemView) {
            
            super(itemView);
            stImage = itemView.findViewById(R.id.stImage);
            stName = itemView.findViewById(R.id.stname);
            
        }
    
        @Override
        public void onClick(View view) {
        
        }
    }
    
    
}