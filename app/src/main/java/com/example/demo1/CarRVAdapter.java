package com.example.demo1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CarRVAdapter extends RecyclerView.Adapter<CarViewHolder> {

    public List<car> cars;
    public CarRVAdapter(List<car> cars) {
        this.cars = cars;
    }
    public CarRVAdapter(Context context){
        CarDBHelper helper = new CarDBHelper(context);
        cars = helper.getAllCars();
    }

    // định nghĩa , ánh xạ các giao diện trên item.xml
    // nạp class ViewHolder cho Apdater sử dụng
    @NonNull
    @Override
    public CarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout,parent,false);
        return new CarViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CarViewHolder holder, int position) {
        car car = cars.get(position);
        holder.tvID.setText(String.valueOf(car.getId()));
        holder.tvName.setText(car.getName());
        holder.tvBrand.setText(car.getBrand());
        holder.tvYear.setText(car.getYear());
        holder.btnDel.setOnClickListener(v ->{
            CarDBHelper helper = new CarDBHelper(v.getContext());
            helper.deleteCar(car.getId());
            // thêm hiệu ứng trượt và làm item bị xóa mất đi
            notifyItemRemoved(position);
            //
            // notifyItemInserted(position);
        });
    }

    @Override
    public int getItemCount() {
        return cars.size();
    }
}
