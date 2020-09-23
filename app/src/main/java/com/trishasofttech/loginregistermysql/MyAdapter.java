package com.trishasofttech.loginregistermysql;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Response;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MeraHolder> {

    Context context;
    String [] name;
    String [] email;
    String [] mobile;
    public MyAdapter(Context context, String[] nameArr, String[] emailArr, String[] mobileArr) {
        super();
        this.context = context;
        this.name = nameArr;
        this.email = emailArr;
        this.mobile = mobileArr;
    }

    @NonNull
    @Override
    public MeraHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        MeraHolder meraHolder = new MeraHolder(v);
        return meraHolder;
    }

    @Override
    public int getItemCount() {
        return name.length;
    }

    @Override
    public void onBindViewHolder(@NonNull MeraHolder holder, int position) {
        holder.tv_name.setText(name[position]);
        holder.tv_mobile.setText(mobile[position]);
        holder.tv_email.setText(email[position]);
    }

    public class MeraHolder extends RecyclerView.ViewHolder {
        TextView tv_name,tv_email,tv_mobile;
        public MeraHolder(@NonNull View itemView) {
            super(itemView);
            tv_email = itemView.findViewById(R.id.tv_email);
            tv_mobile = itemView.findViewById(R.id.tv_mobile);
            tv_name = itemView.findViewById(R.id.tv_name);
        }
    }
}
