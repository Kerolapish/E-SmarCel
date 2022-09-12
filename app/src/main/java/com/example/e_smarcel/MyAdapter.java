package com.example.e_smarcel;

import android.content.Context;
import android.graphics.ColorSpace;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    ArrayList<Model> mList;
    Context context;

    public MyAdapter(Context context, ArrayList<Model> mList) {
        this.mList = mList;
        this.context=context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.activity_item, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

       Model model = mList.get(position);

        holder.TrackingID.setText(model.getTrackNum());
        holder.Specialkey.setText(model.getSpecKey());
        holder.date.setText(model.getDate());

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView TrackingID , Specialkey , date;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            TrackingID = itemView.findViewById(R.id.tracking_text);
            Specialkey = itemView.findViewById(R.id.Specialkeytext);
            date = itemView.findViewById(R.id.date_text);
        }
    }
}
