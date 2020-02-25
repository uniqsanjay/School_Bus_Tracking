package com.example.school_bus_tracking;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.school_bus_tracking.adapters.StudentList_Adapter;
import com.example.school_bus_tracking.com.example.school_bus_tracking.modelview.StringValues;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Student_List extends AppCompatActivity {

    RecyclerView studlist;
    List<StringValues> list;
    ArrayList sname, pname, mob;
    StringValues values;
    StudentList_Adapter stud_adapt;
    DatabaseReference df;
    LinearLayoutManager linearLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student__list);

        studlist = findViewById(R.id.stud_list);
        list = new ArrayList<>();
        sname = new ArrayList<>();
        pname = new ArrayList();
        mob = new ArrayList();
        getStudList();
    }

    public void getStudList(){
        sname.clear();
        mob.clear();
        pname.clear();

        df = FirebaseDatabase.getInstance().getReference("stud_profile");
        df.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                values = new StringValues();
                for(DataSnapshot snap: dataSnapshot.getChildren()){
                    /*values.setStud_name(snap.child("Student_Name").getValue().toString());
                    values.setParent_name(snap.child("Parents_Name").getValue().toString());
                    values.setParent_mob(snap.child("Parents_Mobile").getValue().toString());
                    list.add(values);*/
                    sname.add(snap.child("Student_Name").getValue().toString());
                    mob.add(snap.child("Parents_Mobile").getValue().toString());
                    pname.add(snap.child("Parents_Name").getValue().toString());
                }
                linearLayoutManager = new LinearLayoutManager(Student_List.this);
                studlist.setLayoutManager(linearLayoutManager);
                stud_adapt = new StudentList_Adapter(Student_List.this, sname, mob, pname);
                studlist.setAdapter(stud_adapt);
                stud_adapt.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
