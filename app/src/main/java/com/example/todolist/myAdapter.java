package com.example.todolist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class myAdapter extends RecyclerView.Adapter<myAdapter.myViewHolder> {

    List<Model> models;
    Context context;

    public myAdapter(List<Model> models, Context context) {
        this.models = models;
        this.context = context;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler, parent, false);
        myViewHolder MyViewHolder = new myViewHolder(view);

        return MyViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        Model m = models.get(position);
        holder.ntitle.setText(m.getTitle());
        holder.ndesc.setText(m.getDescription());
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder {

        TextView ntitle;
        TextView ndesc;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            ntitle = itemView.findViewById(R.id.recyTitle);
            ndesc = itemView.findViewById(R.id.recyDesc);
        }
    }
}
