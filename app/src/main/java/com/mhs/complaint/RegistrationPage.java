package com.mhs.complaint;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mhs.complaint.db.DataBaseHelper;
import com.mhs.complaint.prefs.SharedPreferencesHelper;

public class RegistrationPage extends AppCompatActivity {

    EditText name_et, email_et , pass_et, con_pass_et;
    AppCompatButton submit_bt;

    DataBaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_page);

        name_et = findViewById(R.id.name_et);
        email_et = findViewById(R.id.email_et);
        pass_et = findViewById(R.id.pass_et);
        con_pass_et = findViewById(R.id.con_pass_et);

        submit_bt = findViewById(R.id.submit_bt);

        db = new DataBaseHelper(this);

        submit_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = name_et.getText().toString();
                String email = email_et.getText().toString();
                String pass = pass_et.getText().toString();
                String con_pass = con_pass_et.getText().toString();

                if (name.equals("") || email.equals("") || pass.equals("") || con_pass.equals("")){
                    Toast.makeText(RegistrationPage.this, "Please enter all field", Toast.LENGTH_LONG).show();
                }
                else {
                    if (pass.equals(con_pass)){
                        Boolean checkEmail = db.checkEmail(email);
                        if (!checkEmail){
                            Boolean insert = db.insertUserRegistrationData(name, email, pass);
                            if (insert){
                                Toast.makeText(RegistrationPage.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                                SharedPreferencesHelper.insertData(RegistrationPage.this,"UserName",name);
                                Intent intent = new Intent(RegistrationPage.this, UserLoginActivity.class);
                                startActivity(intent);
                                finish();
                            }
                            else {
                                Toast.makeText(RegistrationPage.this, "Registration Failed", Toast.LENGTH_LONG).show();
                            }
                        }
                        else {
                            Toast.makeText(RegistrationPage.this, "User already exits! please login", Toast.LENGTH_LONG).show();
                        }
                    }
                    else {
                        Toast.makeText(RegistrationPage.this, "Passwords not matching", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
}