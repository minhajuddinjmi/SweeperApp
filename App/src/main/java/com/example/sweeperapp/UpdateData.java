package com.example.sweeperapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UpdateData extends AppCompatActivity {

    TextInputEditText updateFullName, updateEmail, updatePhoneNo, updateAddress, updateDistrict, updateState, updatePassword, updateTask;
    Button updateData;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_update_data);

        updateFullName = findViewById(R.id.updateFullName);
        updateEmail = findViewById(R.id.updateEmail);
        updatePhoneNo = findViewById(R.id.updatePhoneNo);
        updateAddress = findViewById(R.id.updateAddress);
        updateDistrict = findViewById(R.id.updateDistrict);
        updateState = findViewById(R.id.updateState);
        updatePassword = findViewById(R.id.updatePassword);
        updateTask = findViewById(R.id.task);

        Intent intent = getIntent();

        String name = intent.getStringExtra("name");
        String email = intent.getStringExtra("email");
        String phoneNo = intent.getStringExtra("phoneNo");
        String address = intent.getStringExtra("address");
        String district = intent.getStringExtra("district");
        String state = intent.getStringExtra("state");
        String password = intent.getStringExtra("password");
        String task = intent.getStringExtra("task");


        showAllData(name, email, phoneNo, address, district, state, password, task);

        updateData = findViewById(R.id.update);
        updateData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("user");

                String name = updateFullName.getText().toString();
                String email = updateEmail.getText().toString();
                String address = updateAddress.getText().toString();
                String district = updateDistrict.getText().toString();
                String state = updateState.getText().toString();
                String phoneNo = updatePhoneNo.getText().toString();
                String password = updatePassword.getText().toString();
                String task = updateTask.getText().toString();

                UserSignUp helperClass = new UserSignUp(name, address, district, state, email, phoneNo, password, task);

                reference.child(phoneNo).setValue(helperClass);

                Toast.makeText(getApplicationContext(), "Your profile has been updated", Toast.LENGTH_LONG).show();

                Intent dashboard = new Intent(getApplicationContext(), Dashboard.class);
                dashboard.putExtra("name", name);
                dashboard.putExtra("address", address);
                dashboard.putExtra("district", district);
                dashboard.putExtra("state", state);
                dashboard.putExtra("email", email);
                dashboard.putExtra("phoneNo", phoneNo);
                dashboard.putExtra("password", password);
                dashboard.putExtra("task", task);
                startActivity(dashboard);
            }
        });
    }

    private void showAllData(String name, String email, String phoneNo, String address, String district, String state, String password, String task) {

        updateFullName.setText(name);
        updateEmail.setText(email);
        updatePhoneNo.setText(phoneNo);
        updateAddress.setText(address);
        updateDistrict.setText(district);
        updateState.setText(state);
        updatePassword.setText(password);
        updateTask.setText(task);
    }
}