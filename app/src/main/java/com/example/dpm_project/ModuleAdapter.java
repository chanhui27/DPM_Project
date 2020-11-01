package com.example.dpm_project;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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

    class ModuleHolder extends RecyclerView.ViewHolder {
        private TextView textViewCode;
        private TextView textViewTitle;

        public ModuleHolder(View itemView) {
            super(itemView);
            textViewCode = itemView.findViewById(R.id.text_view_module_code);
            textViewTitle = itemView.findViewById(R.id.text_view_module_title);

        }

    }
}
