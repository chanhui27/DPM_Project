package com.example.dpm_project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {

    private ArrayList<Module> arrayList;
    private Context context;

    public CustomAdapter(ArrayList<Module> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_module,parent,false);
        CustomViewHolder holder = new CustomViewHolder(view);


        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        Glide.with(holder.itemView)
                .load(arrayList.get(position).getModule())
                .into(holder.iv_profile);
        holder.tv_id.setText(arrayList.get(position).getModule());
        holder.modulec.setText(arrayList.get(position).getModuleCode());
        holder.mdesc.setText(arrayList.get(position).getModuledesc());
    }

    @Override
    public int getItemCount() {
        //formula
        return (arrayList != null ? arrayList.size(): 0);
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_profile;

        TextView tv_id;
        TextView modulec;
        TextView mdesc;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.iv_profile = itemView.findViewById(R.id.id_profile);
            this.tv_id = itemView.findViewById(R.id.tv_id);
            this.modulec = itemView.findViewById(R.id.tv_mc);
            this.mdesc = itemView.findViewById(R.id.tv_mm);

        }
    }
}
