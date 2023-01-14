package com.mhs.complaint;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mhs.complaint.db.DataBaseHelper;
import com.mhs.complaint.prefs.SharedPreferencesHelper;

public class UserLoginActivity extends AppCompatActivity {
    TextView registration_tv;
    EditText email_et, pass_et;
    AppCompatButton login_bt;

    DataBaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);

        registration_tv = findViewById(R.id.registration_tv);

        email_et = findViewById(R.id.email_et);
        pass_et = findViewById(R.id.pass_et);

        login_bt = findViewById(R.id.login_bt);

        db = new DataBaseHelper(this);

        registration_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserLoginActivity.this, RegistrationPage.class);
                startActivity(intent);
            }
        });

        login_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = email_et.getText().toString();
                String password = pass_et.getText().toString();

                if (email.equals("") || password.equals("")){
                    Toast.makeText(UserLoginActivity.this, "Please enter all field", Toast.LENGTH_LONG).show();
                }
                else {
                    Boolean checkEmailPass = db.checkedEmailPassword(email, password);
                    if (checkEmailPass){
                        SharedPreferencesHelper.insertData(UserLoginActivity.this,"isLoggedUser","ok_user");
                        SharedPreferencesHelper.insertData(UserLoginActivity.this,"UserEmail",email);
                        Toast.makeText(UserLoginActivity.this, "Login Successfully", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(UserLoginActivity.this, UserDashboard.class);
                        startActivity(intent);
                        finish();
                    }
                    else {
                        Toast.makeText(UserLoginActivity.this, "Invalid credentials", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
}