package com.android.acadgild.androidversions151.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.acadgild.androidversions151.MainActivity;
import com.android.acadgild.androidversions151.R;
import com.android.acadgild.androidversions151.bean.DataModel;

import java.util.ArrayList;

/**
 * Created by Jal on 18-09-2017.
 * CustomAdapter class for recycler view.
 */


public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    //ArrayList for data
    private ArrayList<DataModel> dataSet;

    //View holder class
    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textViewName;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.textViewName = (TextView) itemView.findViewById(R.id.textViewName);
        }
    }

    //Consturcter
    public CustomAdapter(ArrayList<DataModel> data) {
        this.dataSet = data;
    }


    //onCreateViewHolder override method to inflate layout
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                           int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cards_layout, parent, false);

        //Set on click listner
        view.setOnClickListener(MainActivity.myOnClickListener);
        //Create object of MyViewHolder and return view holder object
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    //onBindViewHolder override method to bind data with view
    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {
        Log.e("Data Size-","Adapter-"+dataSet.size());
        TextView textViewName = holder.textViewName;

        textViewName.setText(dataSet.get(listPosition).getName());
    }

    //getItemCount method to get count of dataSet
    @Override
    public int getItemCount() {
        return dataSet.size();
    }


}