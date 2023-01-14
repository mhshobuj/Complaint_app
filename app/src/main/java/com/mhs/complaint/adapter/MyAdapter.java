package com.mhs.complaint.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mhs.complaint.R;
import com.mhs.complaint.model.ComplaintListModel;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{

    ArrayList<ComplaintListModel> arrayList;
    Context context;

    public MyAdapter(ArrayList<ComplaintListModel> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.complaint_item_card_view,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.title_tv.setText(new StringBuilder().append(arrayList.get(position).getTitle()));
        holder.description_tv.setText(new StringBuilder().append(arrayList.get(position).getDescription()));
        holder.status_tv.setText(new StringBuilder().append(arrayList.get(position).getStatus()));

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public interface RecyclerViewClickListener{
        void onClick(View view, int pos);
    }


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView title_tv, description_tv, status_tv;

        RecyclerViewClickListener listener;

        public void setListener(RecyclerViewClickListener listener) {
            this.listener = listener;
        }

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            title_tv = (TextView) itemView.findViewById(R.id.title_tv);
            description_tv = (TextView) itemView.findViewById(R.id.description_tv);
            status_tv = (TextView) itemView.findViewById(R.id.status_tv);

            //itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onClick(v, getAdapterPosition());
        }
    }
}
