package com.example.dpm_project;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dpm_project.models.Module;

import java.util.ArrayList;
import java.util.List;

public class ManagerModuleAdapter extends RecyclerView.Adapter<ManagerModuleAdapter.ModuleHolder> {
    private List<Module> modules = new ArrayList<>();

    @NonNull
    @Override
    public ModuleHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.manager_module_item, parent, false);
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
            textViewCode = itemView.findViewById(R.id.manager_text_view_module_code);
            textViewTitle = itemView.findViewById(R.id.manager_text_view_module_title);
            relativeLayout = itemView.findViewById(R.id.manager_cardview_relative_layout);

        }

    }
}
