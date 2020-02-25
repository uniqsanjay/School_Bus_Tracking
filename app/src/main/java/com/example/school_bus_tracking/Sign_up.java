package com.example.school_bus_tracking;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Sign_up extends AppCompatActivity {

    TextInputLayout stud_name, course, year, college, address, parent_name, parent_mob, password;
    Button sign_up;
    DatabaseReference df;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        stud_name = findViewById(R.id.stud_name);
        course = findViewById(R.id.course);
        year = findViewById(R.id.year);
        college = findViewById(R.id.college);
        address = findViewById(R.id.address);
        parent_name = findViewById(R.id.parent_name);
        parent_mob = findViewById(R.id.parent_mob);
        password = findViewById(R.id.password);
        sign_up = findViewById(R.id.register);


        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String sname = stud_name.getEditText().getText().toString();
                String cours = course.getEditText().getText().toString();
                String yr = year.getEditText().getText().toString();
                String clg = college.getEditText().getText().toString();
                String add = address.getEditText().getText().toString();
                String pname = parent_name.getEditText().getText().toString();
                String pmob = parent_mob.getEditText().getText().toString();
                String pass = password.getEditText().getText().toString();

                df = FirebaseDatabase.getInstance().getReference("stud_profile").child(pmob);
                df.child("Student_Name").setValue(sname);
                df.child("Student_Course").setValue(cours);
                df.child("Year").setValue(yr);
                df.child("College_Name").setValue(clg);
                df.child("Address").setValue(add);
                df.child("Parents_Name").setValue(pname);
                df.child("Parents_Mobile").setValue(pmob);
                df.child("Password").setValue(pass);
            }
        });
    }
}
