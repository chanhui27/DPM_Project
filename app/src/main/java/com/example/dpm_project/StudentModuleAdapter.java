package com.example.dpm_project;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dpm_project.models.Module;

import java.util.ArrayList;
import java.util.List;

public class StudentModuleAdapter extends RecyclerView.Adapter<StudentModuleAdapter.ModuleHolder> {
    private List<Module> modules = new ArrayList<>();
    private ModuleAdapter.OnITemClickListener listener;

    @NonNull
    @Override
    public ModuleHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.student_module_item, parent, false);
        return new ModuleHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ModuleHolder holder, int position) {
        Module currentModule = modules.get(position);
        holder.imageViewLock.setVisibility(currentModule.isLocked() ? View.VISIBLE : View.GONE);
        holder.relativeLayout.setBackgroundColor(currentModule.getIsCompleted() == 1 ? Color.GREEN : currentModule.isLocked() ? Color.LTGRAY: 0xEBEBEB);

        holder.textViewCode.setText(currentModule.getCode());
        holder.textViewTitle.setText(currentModule.getTitle());


    }

    @Override
    public int getItemCount() {
        return modules.size();
    }

    public void setModules(List<Module> modules) {
        this.modules = modules;
        notifyDataSetChanged();
    }

    public Module getModuleAt(int adapterPosition) {
        return modules.get(adapterPosition);
    }

    class ModuleHolder extends RecyclerView.ViewHolder {
        private TextView textViewCode;
        private TextView textViewTitle;
        private RelativeLayout relativeLayout;
        private FrameLayout frameLayout;
        private ImageView imageViewLock;

        public ModuleHolder(View itemView) {
            super(itemView);
            textViewCode = itemView.findViewById(R.id.student_text_view_module_code);
            textViewTitle = itemView.findViewById(R.id.student_text_view_module_title);
            relativeLayout = itemView.findViewById(R.id.student_cardview_relative_layout);
            imageViewLock = itemView.findViewById(R.id.cardview_lock);


            //click item
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(modules.get(position));
                    }
                }
            });


        }

    }

    public interface OnITemClickListener {
        void onItemClick(Module module);
    }

    public void setOnItemClickListener(ModuleAdapter.OnITemClickListener listener) {
        this.listener = listener;
    }

}
