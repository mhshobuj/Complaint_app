package com.mhs.complaint;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class UserCategoryActivity extends AppCompatActivity {

    AppCompatButton admin_bt, user_bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_category);

        admin_bt = findViewById(R.id.admin_bt);
        user_bt = findViewById(R.id.user_bt);

        admin_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserCategoryActivity.this, AdminLoginActivity.class);
                startActivity(intent);
            }
        });

        user_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserCategoryActivity.this, UserLoginActivity.class);
                startActivity(intent);
            }
        });
    }
}