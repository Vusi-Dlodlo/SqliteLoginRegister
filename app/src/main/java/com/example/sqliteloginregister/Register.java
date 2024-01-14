package com.example.sqliteloginregister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Register extends AppCompatActivity {

    EditText edtEmail, edtPass;
    Button btnReg;

    DBHelper dbHelper;

    TextView loginRed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        dbHelper = new DBHelper(this);

        edtEmail = findViewById(R.id.edtRgTxtEmail);
        edtPass = findViewById(R.id.edtRgTxtPass);

        btnReg = findViewById(R.id.btnReg);
        loginRed = findViewById(R.id.txtLogin);

        btnReg.setOnClickListener(v -> {
            String user, pass;

            user = edtEmail.getText().toString();
            pass = edtPass.getText().toString();

            if(user.equals("") || pass.equals("")){
                Toast.makeText(v.getContext(), "Email and Pass cannot be left blank !!", Toast.LENGTH_SHORT).show();
            }else{
                if(dbHelper.checkUsername(user)){
                   Toast.makeText(v.getContext(), "User Already Exists", Toast.LENGTH_LONG).show();
                   return;
                }
               boolean registeredSuccess =  dbHelper.insertData(user, pass);
               if(registeredSuccess){
                   Toast.makeText(v.getContext(), "User Registered Successfully ", Toast.LENGTH_LONG).show();
                   Intent loginRedirect = new Intent(v.getContext(), Login.class);
                   startActivity(loginRedirect);
                   finish();
               }else{
                   Toast.makeText(v.getContext(), "Failed To register User", Toast.LENGTH_LONG).show();
               }
            }
        });

        loginRed.setOnClickListener(v -> {
            Intent lgnRdrct = new Intent(v.getContext(), Login.class);
            startActivity(lgnRdrct);
            finish();
        });

    }
}