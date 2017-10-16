package com.savitroday.savischools.adapter;

import android.app.Activity;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bignerdranch.expandablerecyclerview.Adapter.ExpandableRecyclerAdapter;
import com.bignerdranch.expandablerecyclerview.Model.ParentObject;
import com.savitroday.savischools.R;
import com.savitroday.savischools.api.response.Assignment;
import com.savitroday.savischools.databinding.HomeworkcellBinding;

import java.util.List;


/**
 * Created by owner on 10/16/2017.
 */

public class HomworkExpandableAdepter extends ExpandableRecyclerAdapter<HomeworkParentViewHolder, HomeWorkChildViewHolder>
{
        Context context;
        List<ParentObject> parentItemList;
        HomeworkcellBinding mBindings;
public HomworkExpandableAdepter (Context context, List<ParentObject> parentItemList) {
        super(context, parentItemList);
        this.context=context;
       this.parentItemList = parentItemList;
        }


        @Override
        public HomeworkParentViewHolder onCreateParentViewHolder(ViewGroup viewGroup) {
                mBindings= DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.homeworkcell,viewGroup, false);

                return new HomeworkParentViewHolder(mBindings.getRoot());
        }

        @Override
        public HomeWorkChildViewHolder onCreateChildViewHolder(ViewGroup viewGroup) {
                mBindings= DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.homeworkcell,viewGroup, false);

                return new HomeWorkChildViewHolder(mBindings.getRoot());
        }

        @Override
        public void onBindParentViewHolder(HomeworkParentViewHolder homeworkParentViewHolder, int i, Object o)
        {


        }

        @Override
        public void onBindChildViewHolder(HomeWorkChildViewHolder homeWorkChildViewHolder, int i, Object o) {

        }
}
