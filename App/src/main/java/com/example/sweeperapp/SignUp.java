package com.example.sweeperapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {

    Button signUp, signInScreen;
    TextInputEditText regFullName, regAddress, regDistrict, regState, regEmail, regPhoneNo, regPassword;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    private Boolean validationName(){
        String val = regFullName.getText().toString();

        if(val.isEmpty()){
            regFullName.setError("This field cannot be empty");
            return false;
        }else{
            regFullName.setError(null);
            return true;
        }
    }

    private Boolean validateAddress() {
        String val = regAddress.getText().toString();

        if (val.isEmpty()) {
            regAddress.setError("Field cannot be empty");
            return false;
        }else{
            regAddress.setError(null);
            return true;
        }
    }

    private Boolean validateDistrict() {
        String val = regDistrict.getText().toString();

        if (val.isEmpty()) {
            regDistrict.setError("Field cannot be empty");
            return false;
        }else{
            regDistrict.setError(null);
            return true;
        }
    }

    private Boolean validateState() {
        String val = regState.getText().toString();

        if (val.isEmpty()) {
            regState.setError("Field cannot be empty");
            return false;
        }else{
            regState.setError(null);
            return true;
        }
    }

    private Boolean validateEmail() {
        String val = regEmail.getText().toString();

        if (val.isEmpty()) {
            regEmail.setError("Field cannot be empty");
            return false;
        } else {
            regEmail.setError(null);
            return true;
        }
    }

    private Boolean validatePhoneNo() {
        String val = regPhoneNo.getText().toString();

        if (val.isEmpty()) {
            regPhoneNo.setError("Field cannot be empty");
            return false;
        } else {
            regPhoneNo.setError(null);
            return true;
        }
    }

    private Boolean validationPassword(){
        String val = regPassword.getText().toString();
        String passwordVal = "^" +
                "(?=.*[0-9])" +         //at least 1 digit
                "(?=.*[a-z])" +         //at least 1 lower case letter
                "(?=.*[A-Z])" +         //at least 1 upper case letter
                "(?=.*[a-zA-Z])" +      //any letter
                "(?=.*[@#$%^&+=])" +    //at least 1 special character
                ".{4,}" +               //at least 4 characters
                "$";
        if (val.isEmpty()) {
            regPassword.setError("Field cannot be empty");
            return false;
        } else if (!val.matches(passwordVal)) {
            regPassword.setError("Password is too weak");
            return false;
        } else {
            regPassword.setError(null);
            return true;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sign_up);

        regFullName = findViewById(R.id.regFullName);
        regAddress = findViewById(R.id.regAddress);
        regDistrict = findViewById(R.id.regDistrict);
        regState = findViewById(R.id.regState);
        regEmail = findViewById(R.id.regEmail);
        regPhoneNo = findViewById(R.id.regPhoneNo);
        regPassword = findViewById(R.id.regPassword);



        signUp = findViewById(R.id.signUp); 
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("user");

                if(!validateEmail() | !validatePhoneNo() | !validatePhoneNo() | !validationPassword() | !validationName() | !validateAddress() | !validateDistrict() | !validateState()){
                    return;
                }

                String name = regFullName.getText().toString();
                String address = regAddress.getText().toString();
                String district = regDistrict.getText().toString();
                String state = regState.getText().toString();
                String email = regEmail.getText().toString();
                String phoneNo = regPhoneNo.getText().toString();
                String password = regPassword.getText().toString();


                UserSignUp helper = new UserSignUp(name, address, district, state, email, phoneNo, password);

                reference.child(phoneNo).setValue(helper);

                Intent dashboard = new Intent(getApplicationContext(), Dashboard.class);
                dashboard.putExtra("name", name);
                dashboard.putExtra("address", address);
                dashboard.putExtra("district", district);
                dashboard.putExtra("state", state);
                dashboard.putExtra("email", email);
                dashboard.putExtra("phoneNo", phoneNo);
                dashboard.putExtra("password", password);
                startActivity(dashboard);

            }
        });

        signInScreen = findViewById(R.id.signInScreen);
        signInScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signIn = new Intent(SignUp.this, SignIn.class);
                startActivity(signIn);
            }
        });

    }
}