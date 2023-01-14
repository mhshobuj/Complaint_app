package com.mhs.complaint;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.mhs.complaint.adapter.MyAdminAdapter;
import com.mhs.complaint.db.DataBaseHelper;
import com.mhs.complaint.model.ComplaintListModel;
import com.mhs.complaint.prefs.SharedPreferencesHelper;

import java.util.ArrayList;

public class AdminDashboard extends AppCompatActivity {

    ArrayList<ComplaintListModel> arrayList;
    RecyclerView recyclerView;
    DataBaseHelper dbHelper;
    ImageView logout_bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        logout_bt = findViewById(R.id.logout_bt);

        dbHelper = new DataBaseHelper(this);
        arrayList = new ArrayList<>();
        Cursor cursor = dbHelper.getData();
        if (cursor.getCount() == 0){
            Toast.makeText(AdminDashboard.this, "Data Not Found", Toast.LENGTH_LONG).show();
        }
        while (cursor.moveToNext()){
            ComplaintListModel model = new ComplaintListModel(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));
            arrayList.add(model);
        }

        MyAdminAdapter myAdapter = new MyAdminAdapter(arrayList,this);
        recyclerView.setAdapter(myAdapter);

        logout_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(AdminDashboard.this)
                        .setTitle("Logout Request")
                        .setMessage("Are you sure you want to logout?")
                        .setPositiveButton(android.R.string.yes, (dialog, which) -> {
                            SharedPreferencesHelper.deleteData(AdminDashboard.this,"isLoggedAdmin");
                            Intent intent = new Intent(AdminDashboard.this, UserCategoryActivity.class);
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