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

public class NewComplaintAdd extends AppCompatActivity {
    AppCompatButton conform_bt;
    EditText title_tv, description_tv;

    DataBaseHelper dataBaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_complaint_add);

        conform_bt = findViewById(R.id.conform_bt);

        title_tv = findViewById(R.id.title_tv);
        description_tv = findViewById(R.id.description_tv);

        dataBaseHelper = new DataBaseHelper(this);

        conform_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = title_tv.getText().toString();
                String des = description_tv.getText().toString();


                if (title.equals("") || des.equals("")){
                    Toast.makeText(NewComplaintAdd.this, "Please enter all field", Toast.LENGTH_LONG).show();
                }
                else {
                    Boolean checkInsertData = dataBaseHelper.insertNewComplaint(title, des, "pending");
                    if (checkInsertData){
                        Toast.makeText(NewComplaintAdd.this, "Successfully add new complaint", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(NewComplaintAdd.this, UserDashboard.class);
                        startActivity(intent);
                        finish();
                    }
                    else {
                        Toast.makeText(NewComplaintAdd.this, "Not Submitted", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}