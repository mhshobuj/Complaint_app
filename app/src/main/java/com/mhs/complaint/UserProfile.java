package com.mhs.complaint;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.mhs.complaint.prefs.SharedPreferencesHelper;

public class UserProfile extends AppCompatActivity {
    TextView name_et, email_et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        name_et = findViewById(R.id.name_et);
        email_et = findViewById(R.id.email_et);

        name_et.setText(SharedPreferencesHelper.retriveData(this,"UserName"));
        email_et.setText(SharedPreferencesHelper.retriveData(this,"UserEmail"));

    }
}