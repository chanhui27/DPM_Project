package com.example.dpm_project;

import android.graphics.Color;
import android.text.NoCopySpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dpm_project.models.Module;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class ModuleAdapter extends RecyclerView.Adapter<ModuleAdapter.ModuleHolder> {
    private List<Module> modules = new ArrayList<>();



    @NonNull
    @Override
    public ModuleHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.module_item, parent, false);
        return new ModuleHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ModuleHolder holder, int position) {
        Module currentModule = modules.get(position);
        if (currentModule.getIsCompleted() == 1){
            holder.relativeLayout.setBackgroundColor(Color.GREEN);
        }else{
            holder.relativeLayout.setBackgroundColor(0xEBEBEB);
        }
        holder.textViewCode.setText(currentModule.getCode());
        holder.textViewTitle.setText(currentModule.getTitle());

    }

    @Override
    public int getItemCount() {
        return modules.size();
    }

    public  void setModules(List<Module> modules){
        this.modules  = modules;
        notifyDataSetChanged();
    }

    public Module getModuleAt(int adapterPosition) {
        return modules.get(adapterPosition);
    }

    class ModuleHolder extends RecyclerView.ViewHolder {
        private TextView textViewCode;
        private TextView textViewTitle;
        private RelativeLayout relativeLayout;

        public ModuleHolder(View itemView) {
            super(itemView);
            textViewCode = itemView.findViewById(R.id.text_view_module_code);
            textViewTitle = itemView.findViewById(R.id.text_view_module_title);
            relativeLayout = itemView.findViewById(R.id.cardview_relative_layout);

        }

    }


}
