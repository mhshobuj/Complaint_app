package com.mhs.complaint;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.mhs.complaint.prefs.SharedPreferencesHelper;

public class UserDashboard extends AppCompatActivity {

    LinearLayout userProfile, emergency, newComplaint, complaintList;
    ImageView logout_bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userProfile = findViewById(R.id.userProfile);
        emergency = findViewById(R.id.emergency);
        newComplaint = findViewById(R.id.newComplaint);
        complaintList = findViewById(R.id.complaintList);
        logout_bt = findViewById(R.id.logout_bt);

        userProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserDashboard.this, UserProfile.class);
                startActivity(intent);
            }
        });

        emergency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserDashboard.this, EmergencyCall.class);
                startActivity(intent);
            }
        });

        newComplaint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserDashboard.this, NewComplaintAdd.class);
                startActivity(intent);
            }
        });

        complaintList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserDashboard.this, ComplaintList.class);
                startActivity(intent);
            }
        });


        logout_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(UserDashboard.this)
                        .setTitle("Logout Request")
                        .setMessage("Are you sure you want to logout?")
                        .setPositiveButton(android.R.string.yes, (dialog, which) -> {
                            SharedPreferencesHelper.deleteData(UserDashboard.this,"isLoggedUser");
                            SharedPreferencesHelper.deleteData(UserDashboard.this,"UserEmail");
                            Intent intent = new Intent(UserDashboard.this, UserCategoryActivity.class);
                            startActivity(intent);
                            finish();

                        })
                        .setNegativeButton(android.R.string.no, null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
        });
    }
}