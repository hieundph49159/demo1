package com.example.demo1;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CarViewHolder extends RecyclerView.ViewHolder {

    final public TextView tvID, tvName, tvBrand, tvYear;
    final public Button btnDel;

    public CarViewHolder(@NonNull View itemView) {
        super(itemView);
        tvID = itemView.findViewById(R.id.tvID);
        tvName = itemView.findViewById(R.id.tvName);
        tvBrand = itemView.findViewById(R.id.tvBrand);
        tvYear = itemView.findViewById(R.id.tvYear);
        btnDel = itemView.findViewById(R.id.btnDelete);
    }
}
