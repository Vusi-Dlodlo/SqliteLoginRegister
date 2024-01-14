package com.example.sqliteloginregister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    Button btnLog;
    EditText edtEmail, edtPass;
    DBHelper dbHelper;
    TextView registerRed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtEmail = findViewById(R.id.edtTxtEmail);
        edtPass = findViewById(R.id.edtTxtPass);
        btnLog = findViewById(R.id.btnLgn);
        dbHelper = new DBHelper(this);
        registerRed = findViewById(R.id.txtReg);

        btnLog.setOnClickListener(v -> {
            boolean logginAttempt = dbHelper.chekUser(edtEmail.getText().toString(), edtPass.getText().toString());

            if (logginAttempt) {
                Intent i = new Intent(v.getContext(), MainActivity.class);
                startActivity(i);
                finish();
            } else {
                Toast.makeText(v.getContext(), "Loggin Attempt Failed", Toast.LENGTH_LONG).show();
            }
        });

        registerRed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent regRedirect = new Intent(v.getContext(), Register.class);
                startActivity(regRedirect);
                finish();
            }
        });

    }
}