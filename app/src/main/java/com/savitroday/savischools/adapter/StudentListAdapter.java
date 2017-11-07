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
import com.savitroday.savischools.helper.OnItemClickListener;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by owner on 9/26/2017.
 */

public class StudentListAdapter extends RecyclerView.Adapter<StudentListAdapter.viewHolder> {
    Context _context;
    List<Student> studentList;
    View view;
    private final OnItemClickListener listener;
    
    public StudentListAdapter(Context _context, List<Student> studentList, OnItemClickListener listener) {
        this._context = _context;
        this.studentList = studentList;
        this.listener = listener;
    }
    
    
    @Override
    public StudentListAdapter.viewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.studentlist, parent, false);
        return new viewHolder(view);
    }
    
    @Override
    public void onBindViewHolder(StudentListAdapter.viewHolder holder, int position) {
        final Student student = studentList.get(position);
        if (student.iconMediaPath != null)
            Picasso.with(_context)
                    .load(student.iconMediaPath)
                    .placeholder(R.drawable.profile_picture)
                    .into(holder.stImage);
        holder.stName.setText(student.studentName);
        if (student.isdefault) {
            holder.stImage.setBorderColor(ContextCompat.getColor(_context, R.color.white));
        } else {
            holder.stImage.setBorderColor(ContextCompat.getColor(_context, android.R.color.transparent));
        }
    
        holder.bind(studentList.get(position), listener);
    
    }
    
    
    @Override
    public int getItemCount() {
        return studentList.size();
    }
    
    class viewHolder extends RecyclerView.ViewHolder {
        
        CircleImageView stImage;
        TextView stName;
        
        public viewHolder(View itemView) {
            
            super(itemView);
            stImage = itemView.findViewById(R.id.stImage);
            stName = itemView.findViewById(R.id.stname);
            
        }
        
        public void bind(final Student item, final OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                
                @Override
                public void onClick(View v) {
                    
                    listener.onItemClick(item);
                    
                }
            });
            
        }
        
    }
    
    
}