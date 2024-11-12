package com.example.demo1;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class CarAdapter extends BaseAdapter {
    List<car> cars;


    public CarAdapter(List<car> cars){
        this.cars = cars;
    }
    @Override
    public int getCount() {
        return cars.size();
    }

    @Override
    public car getItem(int i) {
        return cars.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public CarHolder holder;

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        // hàm này được gọi vào mỗi khi người dùng cuộn lisview
        // được gọi khi row này đươc hiển thị trên màn hình
        // viewGroup : chính là listview chứa adapter
        if (view == null) {
            view = LayoutInflater.from(viewGroup.getContext()).
                    inflate(R.layout.layout, viewGroup, false);
            holder = new CarHolder();
            holder.tvID = view.findViewById(R.id.tvID);
            holder.tvName = view.findViewById(R.id.tvName);
            holder.tvBrand = view.findViewById(R.id.tvBrand);
            holder.tvYear = view.findViewById(R.id.tvYear);
            Log.e("getView", "Vị trí được trả về " + i);
            // nạp holder vào tag của view : lưu tại
            view.setTag(holder);
        }
        else {
            holder = (CarHolder) view.getTag();
        }
            car car = getItem(i);
            holder.tvID.setText(car.getId() + "");
            holder.tvName.setText(car.getName());
            holder.tvBrand.setText(car.getBrand());
            holder.tvYear.setText(car.getYear());

            view.findViewById(R.id.btnDelete).setOnClickListener(v ->{
                CarDBHelper carDBHelper = new CarDBHelper(viewGroup.getContext());
                long kq = carDBHelper.deleteCar(car.getId());
                if (kq > 0){
                    cars.remove(i);
                    notifyDataSetChanged();
                }
            });

        return view;
    }
    class CarHolder{
        public TextView tvID;
        public TextView tvName;
        public TextView tvBrand;
        public TextView tvYear;
    }
}
