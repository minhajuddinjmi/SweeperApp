package com.example.sweeperapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class SignIn extends AppCompatActivity {

    //FirebaseDatabase variable
    FirebaseDatabase rootNode;
    DatabaseReference reference;

    //Required button and input field data
    Button signIn, signUpScreen;
    TextInputEditText phoneNumber, password;

    //Validation for user input data to sign in
    private Boolean validatePhoneNo() {
        String val = phoneNumber.getText().toString();

        if (val.isEmpty()) {
            phoneNumber.setError("Field cannot be empty");
            return false;
        }else{
            phoneNumber.setError(null);
            return true;
        }
    }

    private Boolean validationPassword(){
        String val = password.getText().toString();
        if (val.isEmpty()) {
            password.setError("Field cannot be empty");
            return false;
        } else {
            password.setError(null);
            return true;
        }
    }


    //check user is exits or not in firebase database.
    //If user's phone number and password is correct then move to dashboard screen.
    private void isUser(){
        final String userEnterPhoneNo = phoneNumber.getText().toString().trim();
        final String userEnterPassword = password.getText().toString().trim();

        try {
            //Connect firebase database from user
            rootNode = FirebaseDatabase.getInstance();
            reference = rootNode.getReference("user");

            //Check user's phone no. is valid or not
            Query checkPhoneNo = reference.orderByChild("phoneNo").equalTo(userEnterPhoneNo);
        checkPhoneNo.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    phoneNumber.setError(null);

                    //if user's phone no. is exists then check password
                    String passwordFromDB = snapshot.child(userEnterPhoneNo).child("password").getValue(String.class);
                    if (passwordFromDB.equals(userEnterPassword)) {
                        phoneNumber.setError(null);

                        //user's verified so reterive data from firebase database.
                        String nameFromDB = snapshot.child(userEnterPhoneNo).child("fullName").getValue(String.class);
                        String emailFromDB = snapshot.child(userEnterPhoneNo).child("email").getValue(String.class);
                        String phoneNoFromDB = snapshot.child(userEnterPhoneNo).child("phoneNo").getValue(String.class);
                        String addressFromDB = snapshot.child(userEnterPhoneNo).child("address").getValue(String.class);
                        String districtFromDB = snapshot.child(userEnterPhoneNo).child("district").getValue(String.class);
                        String stateFromDB = snapshot.child(userEnterPhoneNo).child("state").getValue(String.class);
                        String passwordFromDB_1 = snapshot.child(userEnterPhoneNo).child("password").getValue(String.class);
                        String taskFromDB = snapshot.child(userEnterPhoneNo).child("task").getValue(String.class);

                        //move to dashboard screen and send reterive data to dashboard screen.
                        Intent dashboard = new Intent(getApplicationContext(), Dashboard.class);
                        dashboard.putExtra("name", nameFromDB);
                        dashboard.putExtra("address", addressFromDB);
                        dashboard.putExtra("district", districtFromDB);
                        dashboard.putExtra("state", stateFromDB);
                        dashboard.putExtra("email", emailFromDB);
                        dashboard.putExtra("phoneNo", phoneNoFromDB);
                        dashboard.putExtra("password", passwordFromDB_1);
                        dashboard.putExtra("task", taskFromDB);
                        startActivity(dashboard);
                    } else {
                        //user's password is wrong
                        password.setError("Wrong Password");
                        password.requestFocus();
                    }
                } else {
                    //user is not exists in firebase database
                    phoneNumber.setError("No such User exist");
                    phoneNumber.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        }
        catch (Exception e){
            Toast.makeText(getApplicationContext(), "Connection Error", Toast.LENGTH_LONG).show();
        }

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sign_in);


        signIn = findViewById(R.id.signIn);
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //connect the user's phoneNo and user's password from signin screen;
                phoneNumber = findViewById(R.id.phoneNumber);
                password = findViewById(R.id.password);
                isUser();
            }
        });

        signUpScreen = findViewById(R.id.signUpScreen);
        signUpScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signUp = new Intent(SignIn.this, SignUp.class);
                startActivity(signUp);
            }
        });
    }
}