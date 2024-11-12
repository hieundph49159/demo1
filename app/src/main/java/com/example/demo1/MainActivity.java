package com.example.demo1;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    //SQLite
    //Bước 1 :
    // Khởi tạo clas model : tham chiếu đối tường, thược tinính cần lưu chữ
    // id,name,brand,year
    //tạo 1 class java tên là car.java - có các biến là id,name,brand và year, tất cả đều là string, id là int
    //Bước 2:khởi tạo class - lớp khai báo thông tin cho CSDL
    // tên file chứa dữ liệu
    // các Bảng - khai báo bảng - - khởi tạo bảng
    // SqliteOpenHelper
    //Bước 3:
    // định nghĩa các hàm CRUD
    // tạo
    // update
    // xóa
    // danh sách
    // tìm kiếm

    List<car> cars = new ArrayList<>();
    CarAdapter carAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // khởi tạo class : sử dụng từ khóa là new
        // tên class = new + hàm Contructer
        // khởi tạo SQLite để sử dùng

        CarDBHelper carDBHelper = new CarDBHelper(MainActivity.this);

        cars = carDBHelper.getAllCars();
        carAdapter = new CarAdapter(cars);
        ListView lvList = findViewById(R.id.lvList);
        lvList.setAdapter(carAdapter);
        // thong bao cap nhat du lieu vao list
        //carAdapter.notifyDataSetChanged();

        carAdapter.notifyDataSetChanged();

        EditText edtID = findViewById(R.id.edtId);
        EditText edtName = findViewById(R.id.edtName);
        EditText edtBrand = findViewById(R.id.edtBrand);
        EditText edtYear = findViewById(R.id.edtYear);

        findViewById(R.id.btnSave).setOnClickListener(v ->{
            int id = Integer.parseInt(edtID.getText().toString());
            String name = edtName.getText().toString();
            String brand = edtBrand.getText().toString();
            String year = edtYear.getText().toString();
            // khởi tạo Car với các thông tin lấy được
           car car = new car(id,name,brand,year);
           long ketqua = carDBHelper.insert(car);
           if(ketqua > 0 ){
               cars.add(car);
               carAdapter.notifyDataSetChanged();
               Toast.makeText(this, "Thành công", Toast.LENGTH_SHORT).show();
           }
           else
               Toast.makeText(this, "Có lỗi xảy ra!!!", Toast.LENGTH_SHORT).show();


        });
        
        findViewById(R.id.btnUpdate).setOnClickListener(v -> {
            int id = Integer.parseInt(edtID.getText().toString());
            String name = edtName.getText().toString();
            String brand = edtBrand.getText().toString();
            String year = edtYear.getText().toString();

            car car = new car(id,name,brand,year);
            long ketqua = carDBHelper.updateCar(car);
            if (ketqua > 0) {
                cars = carDBHelper.getAllCars();
                carAdapter = new CarAdapter(cars);
                Toast.makeText(this, "Thanh Cong!!!", Toast.LENGTH_SHORT).show();
            } else Toast.makeText(this, "Co loi xay ra!!!", Toast.LENGTH_SHORT).show();

        });

    }
}