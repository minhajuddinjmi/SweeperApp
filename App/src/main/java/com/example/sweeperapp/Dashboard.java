package com.example.sweeperapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.EventListener;

public class Dashboard extends AppCompatActivity {

    //Profile data variable
    TextInputEditText profileFullName, profileEmail, profilePhoneNo, profileAddress, profileDistrict, profileState, profilePassword, profileTask;

    //view on title
    TextView fullName, email_1;

    //update button
    Button updateDate,completeTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_dashboard);

        //connect profile data variable via id's.
        profileFullName = findViewById(R.id.profileFullName);
        profileEmail = findViewById(R.id.profileEmail);
        profilePhoneNo = findViewById(R.id.profilePhoneNo);
        profileAddress = findViewById(R.id.profileAddress);
        profileDistrict = findViewById(R.id.profileDistrict);
        profileState = findViewById(R.id.profileState);
        profilePassword = findViewById(R.id.profilePassword);
        profileTask = findViewById(R.id.task);

        fullName = findViewById(R.id.fullname);
        email_1 = findViewById(R.id.email_1);

        //showAllData
        showAllData();

        //click on update button then it get data and move to updateData screen.
        updateDate = findViewById(R.id.updateData);
        updateDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dashboard = new Intent(getApplicationContext(), UpdateData.class);
                dashboard.putExtra("name", profileFullName.getText().toString());
                dashboard.putExtra("email", profileEmail.getText().toString());
                dashboard.putExtra("phoneNo", profilePhoneNo.getText().toString());
                dashboard.putExtra("address", profileAddress.getText().toString());
                dashboard.putExtra("district", profileDistrict.getText().toString());
                dashboard.putExtra("state", profileState.getText().toString());
                dashboard.putExtra("password", profilePassword.getText().toString());
                dashboard.putExtra("task", profileTask.getText().toString());
                startActivity(dashboard);
            }
        });

        completeTask = findViewById(R.id.complete_task);
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("user").child(profilePhoneNo.getText().toString());
        completeTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ref.child("task").setValue("Complete");
                profileTask.setText("Complete");
            }
        });
    }

    //This method get the data from privious screen and show data in the dashboard
    private void showAllData(){
        Intent intent = getIntent();

        String name = intent.getStringExtra("name");
        String email = intent.getStringExtra("email");
        String phoneNo = intent.getStringExtra("phoneNo");
        String address = intent.getStringExtra("address");
        String district = intent.getStringExtra("district");
        String state = intent.getStringExtra("state");
        String password = intent.getStringExtra("password");
        String task = intent.getStringExtra("task");

        fullName.setText(name);
        email_1.setText(email);

        profileFullName.setText(name);
        profileEmail.setText(email);
        profilePhoneNo.setText(phoneNo);
        profileAddress.setText(address);
        profileDistrict.setText(district);
        profileState.setText(state);
        profilePassword.setText(password);
        profileTask.setText(task);

    }
}