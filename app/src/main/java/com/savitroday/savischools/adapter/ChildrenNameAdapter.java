package com.savitroday.savischools.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.savitroday.savischools.api.response.Student;
import com.savitroday.savischools.databinding.StudentnameCellBinding;

import java.util.List;

/**
 * Created by owner on 10/10/2017.
 */

public class ChildrenNameAdapter extends RecyclerView.Adapter<ChildrenNameAdapter.MyViewHolder> {
    List<Student> studentList;
    Context context;
    LayoutInflater layoutInflater;
    
    public class MyViewHolder extends RecyclerView.ViewHolder {
        
        StudentnameCellBinding mBinding;
        
        public MyViewHolder(StudentnameCellBinding mBinding) {
            super(mBinding.getRoot());
            this.mBinding = mBinding;
        }
    }
    
    public ChildrenNameAdapter(Context context, List<Student> students) {
        this.context = context;
        this.studentList = students;
        this.layoutInflater = (LayoutInflater)
                                      context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
    }
    
    
    @Override
    public ChildrenNameAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        StudentnameCellBinding binding = StudentnameCellBinding.inflate(layoutInflater,
                parent, false);
        return new MyViewHolder(binding);
    }
    
    @Override
    public void onBindViewHolder(ChildrenNameAdapter.MyViewHolder holder, int position) {
        holder.mBinding.setStudent(studentList.get(position));
        
    }
    
    @Override
    public int getItemCount() {
        return studentList.size();
    }
}