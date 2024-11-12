package com.example.demo1;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RVActivity extends AppCompatActivity {
    // RecyclerView thay the cho ListView
    // 1/ Dữ liệu : List hoặc ArrayList
    // 2/ 1 Adapter
    // 3/ 1 ViewHolder
    // 4/ RecyclerView và LayoutManager : tùy chỉnh dạng hàng ngang , dọc hay dạng lưới

    List<car> cars = new ArrayList<>();

    private RecyclerView rvList;
    // 4 đặc điểm hơn so với ListView
    // 1. hỗ trợ sẵn ViewHolder giúp tối ưu tốc độ xử lý
    // 2. hỗ các hàm có chức năng đặc thù mà listView ko có :
    // notifyDataSetChanged() : 1 loạt các hàm notìy ví dụ : notifyDataSetChangedAt(), notifyDelete (đi kèm animation đẹp mắt)
    // .....
    // 3. Hỗ trợ mutil type view trên List
    // 4. Hỗ trợ 4 - 5 kiểu hiển thị , sắp xếp hàng theo chiều ngang ,dọc, lưới, so le ..
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rv_activity);
        CarDBHelper helper = new CarDBHelper(this);
        cars = helper.getAllCars();
        rvList = findViewById(R.id.rvListCar);
        CarRVAdapter carAdapter = new CarRVAdapter(cars);
        rvList.setAdapter(carAdapter);
        //LayoutManager: tùy chọn xem List sẽ ở ngang, dọc hay dạng luới, hay dạng sole
        //doc

        LinearLayoutManager manager = new LinearLayoutManager(this);
        // ngang
        //manager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        // luoi - gird
        //GridLayoutManager gridLayoutManager = new GridLayoutManager(this,4,RecyclerView.VERTICAL,false);
        rvList.setLayoutManager(manager);
    }
}
