package com.example.demo1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class CarDBHelper extends SQLiteOpenHelper {
    // SQLiteOpenHelper là 1 class dạng asbtract : phải implement các hàm abtract có trong lớp cha

    // ham khởi tạo cho DB, truyền vào tên file,
    // giá trị version của DB
    // truyền vào context của màn hình đang truy cập vào DB
    // null : là tùy chọn ko sử dụng nên truyền là null

    public CarDBHelper(Context context){
        super(context,"tenFile.db",null,1);
    }

    // nơi khởi tạo, khai báo bảng - table
    @Override
    public void onCreate(SQLiteDatabase db) {
        String car_table = "create table car(id integer primary key, name text, brand text, year text)";
        db.execSQL(car_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }

    public long insert(car car){
        long ketqua = -1;
        //1- lấy lớp truy vấn BD từ lớp cha
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        //2- ghép giá trị của đối tượng, khớp với cột tương ứng
        ContentValues values = new ContentValues();
        values.put("id",car.getId());
        values.put("name",car.getName());
        values.put("brand",car.getBrand());
        values.put("year",car.getYear());
        // thông qua tên cột được khai báo dòng 21
        //3- lấy kết quả
        ketqua = (int) sqLiteDatabase.insert("car",null,values);
        // lưu ý : các thuộc tính chữ màu xanh cần KHỚP với tên bảng và tên cột tạo ở dòng 24
        // tính cả chữ hoa - thườngx`

        return ketqua;
    }
    public List<car> getAllCars(){
        List<car> cars = new ArrayList<>();
        String query = "select * from car";
        // thực thi câu lệnh query
        Cursor cursor = getWritableDatabase().rawQuery(query, null);
        if (cursor != null && cursor.getCount() > 0){
            Log.e("COUNT", cursor.getCount() + "");
            while (cursor.moveToNext()){
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String brand = cursor.getString(2);
                String year = cursor.getString(3);
                car  car = new car(id,name,brand,year);
                cars.add(car);

            }
            Log.e("COUNT 2", cars.size() + "");
            // close ket noi
            cursor.close();
        }
        return cars;
    }

    public long deleteCar(int id){
        return getReadableDatabase().delete("car","id = ?",new String[]{String.valueOf(id)});
    }
    public long updateCar(car car) {
        long ketqua = -1;
        // 1/lấy lớp truy vấn DB từ lớp cha
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        // 2/ ghép giá trị của đối tượng , khớp với cột tương ứng
        ContentValues values = new ContentValues();
        values.put("name", car.getName());
        values.put("brand", car.getBrand());
        values.put("year", car.getYear());
        // thông qua tên cột được khai báo ở dòng 24
        // 3/ lấy kết quả
        ketqua = sqLiteDatabase.update("car", values, "id = ?",
                new String[]{ String.valueOf(car.getId())});
        // lưu ý : các thuộc tính chữ màu xanh cần KHỚP với tên bảng và tên cột tạo ở dòng 24
        // tính cả chữ hoa - thường
        return ketqua;
    }

}
