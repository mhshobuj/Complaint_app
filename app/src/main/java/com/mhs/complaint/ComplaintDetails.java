package com.mhs.complaint;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mhs.complaint.db.DataBaseHelper;
import com.mhs.complaint.prefs.SharedPreferencesHelper;

public class ComplaintDetails extends AppCompatActivity {
    TextView title_tv, description_tv;
    EditText status_et;
    AppCompatButton update_bt;

    String email = null, name, number, email_u, password;
    int id;

    DataBaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint_details);

        dbHelper = new DataBaseHelper(this);

        title_tv = findViewById(R.id.title_tv);
        description_tv = findViewById(R.id.description_tv);
        status_et = findViewById(R.id.status_et);

        update_bt = findViewById(R.id.update_bt);

        if (getIntent() != null){
            id = getIntent().getIntExtra("ID", 0);
            Cursor cursor = dbHelper.getDataByID(String.valueOf(id));

            if (cursor.getCount() == 0){
                Toast.makeText(ComplaintDetails.this, "Data Not Found", Toast.LENGTH_LONG).show();
                return;
            }
            else {
                while (cursor.moveToNext()){
                    title_tv.setText(new StringBuilder().append(cursor.getString(1)));
                    description_tv.setText(new StringBuilder().append(cursor.getString(2)));
                    status_et.setText(new StringBuilder().append(cursor.getString(3)));
                }
            }
        }
        else {
            Toast.makeText(ComplaintDetails.this, "Order ID not found", Toast.LENGTH_LONG).show();
        }

        update_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title_u = title_tv.getText().toString();
                String des_u = description_tv.getText().toString();
                String status_u = status_et.getText().toString();

                Boolean updateDate = dbHelper.updateComplaintData(String.valueOf(id),title_u, des_u, status_u);
                if (updateDate){
                    Intent intent = new Intent(ComplaintDetails.this, AdminDashboard.class);
                    startActivity(intent);
                    finish();
                    Toast.makeText(ComplaintDetails.this, "Updated", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(ComplaintDetails.this, "Not updated", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}