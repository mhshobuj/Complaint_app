package com.mhs.complaint;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mhs.complaint.prefs.SharedPreferencesHelper;

public class AdminLoginActivity extends AppCompatActivity {

    EditText email_et, pass_et;
    AppCompatButton login_bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        login_bt = findViewById(R.id.login_bt);

        email_et = findViewById(R.id.email_et);
        pass_et = findViewById(R.id.pass_et);

        login_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = email_et.getText().toString();
                String pass = pass_et.getText().toString();
                if (email.equals("") || pass.equals("")){
                    Toast.makeText(AdminLoginActivity.this, "Please enter all field", Toast.LENGTH_LONG).show();
                }
                else {
                    if (email_et.getText().toString().trim().equals("admin@gmail.com") && pass_et.getText().toString().trim().equals("12345")){
                        SharedPreferencesHelper.insertData(AdminLoginActivity.this,"isLoggedAdmin","ok_admin");
                        Intent intent = new Intent(AdminLoginActivity.this, AdminDashboard.class);
                        Toast.makeText(AdminLoginActivity.this, "Login Successfully", Toast.LENGTH_LONG).show();
                        startActivity(intent);
                        finish();
                    }
                    else {
                        Toast.makeText(AdminLoginActivity.this, "Wrong user or password", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}