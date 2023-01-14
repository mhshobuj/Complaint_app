package com.mhs.complaint;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import com.mhs.complaint.adapter.MyAdapter;
import com.mhs.complaint.db.DataBaseHelper;
import com.mhs.complaint.model.ComplaintListModel;

import java.util.ArrayList;

public class ComplaintList extends AppCompatActivity {

    ArrayList<ComplaintListModel> arrayList;
    RecyclerView recyclerView;
    DataBaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint_list);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        dbHelper = new DataBaseHelper(this);
        arrayList = new ArrayList<>();

        Cursor cursor = dbHelper.getData();
        if (cursor.getCount() == 0){
            Toast.makeText(ComplaintList.this, "Data Not Found", Toast.LENGTH_LONG).show();
        }
        while (cursor.moveToNext()){
            ComplaintListModel model = new ComplaintListModel(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));
            arrayList.add(model);
        }

        MyAdapter myAdapter = new MyAdapter(arrayList,this);
        recyclerView.setAdapter(myAdapter);
    }
}