package com.example.school_bus_tracking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {

    TextInputLayout umob, upass;
    TextView acct;
    Button login;
    DatabaseReference df;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        umob = findViewById(R.id.username);
        upass = findViewById(R.id.password);
        acct = findViewById(R.id.new_account);
        login = findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String mob = umob.getEditText().getText().toString();
                final String pass = upass.getEditText().getText().toString();
                if(mob.equalsIgnoreCase("admin") && pass.equalsIgnoreCase("admin")){
                    Toast.makeText(Login.this, "Admin Login Successfull", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Login.this, Student_List.class));
                }else{
                    df = FirebaseDatabase.getInstance().getReference("stud_profile").child(mob);
                    df.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if(dataSnapshot.child("Password").getValue().equals(upass.getEditText().getText().toString())){
                                Toast.makeText(Login.this, "Student Login Successfull", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(Login.this, "Login Failed", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
                //config.login(Login.this, uname.getEditText().getText().toString(), pass.getEditText().getText().toString());
            }
        });

        acct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this, Sign_up.class));
            }
        });
    }
}
