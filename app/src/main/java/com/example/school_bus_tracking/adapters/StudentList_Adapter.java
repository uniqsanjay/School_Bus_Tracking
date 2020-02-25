package com.example.school_bus_tracking.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.school_bus_tracking.R;
import com.example.school_bus_tracking.com.example.school_bus_tracking.modelview.StringValues;

import java.util.ArrayList;
import java.util.List;

public class StudentList_Adapter extends RecyclerView.Adapter<StudentList_Adapter.MyViewholder> {

    List<StringValues> studvalues;
    ArrayList sname, mob, pname;
    Context context;
    public StudentList_Adapter(Context context, ArrayList sname, ArrayList mob, ArrayList pname) {
        this.context = context;
        this.sname = sname;
        this.mob = mob;
        this.pname = pname;
    }

    @NonNull
    @Override
    public MyViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewholder = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_studlist, parent, false);
        return new MyViewholder(viewholder);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewholder holder, int position) {
        //StringValues values = studvalues.get(position);
        holder.studname.setText(sname.get(position).toString());
        holder.parentname.setText(pname.get(position).toString());
        holder.mob.setText(mob.get(position).toString());
        holder.imgview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Working on this", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return sname.size();
    }

    public class MyViewholder extends RecyclerView.ViewHolder {
        TextView studname, parentname, mob;
        ImageView imgview;
        public MyViewholder(@NonNull View itemView) {
            super(itemView);
            studname = itemView.findViewById(R.id.sname);
            parentname = itemView.findViewById(R.id.pname);
            mob = itemView.findViewById(R.id.pmob);
            imgview = itemView.findViewById(R.id.view);
        }
    }
}
