package db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import bean.CarAccident;
import bean.CarAccidentDetail;
import bean.CarDetail;
import bean.CityDetail;

/**
 * Created by Administrator on 2015/8/11.
 */
public class CarDB {
    public static final String DB_NAME = "car_accident";

    public static final int DB_VERSION = 1;
    private static CarDB carDB;
    private SQLiteDatabase db;

    private CarDB(Context context) {
        DBHelper dbHelper = new DBHelper(context, DB_NAME, null, DB_VERSION);
        db = dbHelper.getWritableDatabase();
    }

    public synchronized static CarDB getInstance(Context context) {
        if (carDB == null) {
            carDB = new CarDB(context);
        }
        return carDB;
    }

    public void saveCarAccident(CarAccident carAccident) {
        List<CarAccidentDetail> list = carAccident.getList();
        if (carAccident != null) {
            ContentValues values = new ContentValues();
            values.put("province", carAccident.getProvince());
            values.put("city", carAccident.getCity());
            values.put("hphm", carAccident.getHphm());
            values.put("hpzl", carAccident.getHpzl());
            for (int i = 0; i < list.size(); i++) {
                CarAccidentDetail carAccidentDetail = new CarAccidentDetail();
                carAccidentDetail = list.get(i);
                values.put("date", carAccidentDetail.getData());
                values.put("area", carAccidentDetail.getArea());
                values.put("act", carAccidentDetail.getAct());
                values.put("code", carAccidentDetail.getCode());
                values.put("fen", carAccidentDetail.getFen());
                values.put("money", carAccidentDetail.getMoney());
                values.put("handled", carAccidentDetail.getHandled());
                db.insert("caraccidenttable", null, values);
            }
        }
    }


    public void saveCityDetail(CityDetail cityDetail) {

        if (cityDetail != null) {
            ContentValues values = new ContentValues();
            values.put("province", cityDetail.getProvince());
            values.put("province_code", cityDetail.getProvince_code());
            values.put("city_name", cityDetail.getCity_name());
            values.put("city_code", cityDetail.getCity_code());
            values.put("abbr", cityDetail.getAbbr());
            values.put("engine", cityDetail.getEngine());
            values.put("engineno", cityDetail.getEngineno());
            values.put("classa", cityDetail.getClassa());
            values.put("classno", cityDetail.getClassno());
            values.put("regist", cityDetail.getRegist());
            values.put("registno", cityDetail.getRegistno());
            db.insert("provincecitytable", null, values);

        }

    }

    public Boolean loadCityDetailBoolean() {
        Cursor cursor = db.query("provincecitytable", null, null, null, null, null, null);
        if (cursor.getCount() != 0) {
            return true;
        }
        return false;
    }
    public CityDetail queryEngineNum(CityDetail cityDetail){
        String province = cityDetail.getProvince();
        String city = cityDetail.getCity_name();
        Cursor cursor = db.rawQuery("select * from provincecitytable where province = ? and city_name = ?",new String[]{province,city});
        if (cursor.moveToFirst()){
            cityDetail.setEngine(cursor.getString(cursor.getColumnIndex("engine")));
            cityDetail.setEngineno(cursor.getString(cursor.getColumnIndex("engineno")));
            cityDetail.setClassa(cursor.getString(cursor.getColumnIndex("classa")));
            cityDetail.setClassno(cursor.getString(cursor.getColumnIndex("classno")));
        }
        cursor.close();
        return cityDetail;
    }

    public List<CityDetail> loadCityDetail() {
        List<CityDetail> list = new ArrayList<CityDetail>();
        Cursor cursor = db.query("provincecitytable", null, null, null, null, null, null);
        if (cursor.moveToFirst())
            do {


                CityDetail city = new CityDetail();
                city.setProvince(cursor.getString(cursor.getColumnIndex("province")));
                city.setProvince_code(cursor.getString(cursor.getColumnIndex("province_code")));
                city.setCity_name(cursor.getString(cursor.getColumnIndex("city_name")));
                city.setCity_code(cursor.getString(cursor.getColumnIndex("city_code")));
                city.setAbbr(cursor.getString(cursor.getColumnIndex("abbr")));
                city.setEngine(cursor.getString(cursor.getColumnIndex("engine")));
                city.setEngineno(cursor.getString(cursor.getColumnIndex("engineno")));
                city.setClassa(cursor.getString(cursor.getColumnIndex("classa")));
                city.setClassno(cursor.getString(cursor.getColumnIndex("classno")));
                city.setRegist(cursor.getString(cursor.getColumnIndex("regist")));
                city.setRegistno(cursor.getString(cursor.getColumnIndex("registno")));

                list.add(city);
            } while (cursor.moveToNext());
        cursor.close();
        return list;
    }


    public void saveCarDetail(CarDetail carDetail) {
        if (carDetail != null) {
            ContentValues values = new ContentValues();
            values.put("province", carDetail.getProvince());
            values.put("city", carDetail.getCity());
            values.put("shortprovince", carDetail.getShortProvince());
            values.put("charcity", carDetail.getCharCity());
            values.put("carnumberstring", carDetail.getCarNumberString());
            values.put("enginenumber", carDetail.getEnginenumber());
            values.put("chejianumber", carDetail.getChejianumber());
            db.insert("cardetailtable", null, values);
//            db.close();
        }

    }

    public List<CarDetail> loadCarDetail() {
        List<CarDetail> list = new ArrayList<CarDetail>();
        Cursor cursor = db.query("cardetailtable", null, null, null, null, null, null);
        if (cursor.moveToFirst())
            do {


                CarDetail car = new CarDetail();

                car.setProvince(cursor.getString(cursor.getColumnIndex("province")));
                car.setCity(cursor.getString(cursor.getColumnIndex("city")));
                car.setShortProvince(cursor.getString(cursor.getColumnIndex("shortprovince")));
                car.setCharCity(cursor.getString(cursor.getColumnIndex("charcity")));
                car.setCarNumberString(cursor.getString(cursor.getColumnIndex("carnumberstring")));
                car.setEnginenumber(cursor.getString(cursor.getColumnIndex("enginenumber")));
                car.setChejianumber(cursor.getString(cursor.getColumnIndex("chejianumber")));
                list.add(car);
            } while (cursor.moveToNext());
        cursor.close();
        return list;
    }

    public CarDetail queryCarDetail(String province, String city, String hm) {
        CarDetail car = new CarDetail();
        Cursor cursor = db.rawQuery("select * from cardetailtable where shortprovince =? and charcity =? and carnumberstring = ?", new String[]{province, city, hm});
        if (cursor.moveToFirst()) {
            car.setProvince(cursor.getString(cursor.getColumnIndex("province")));
            car.setCity(cursor.getString(cursor.getColumnIndex("city")));
            car.setShortProvince(cursor.getString(cursor.getColumnIndex("shortprovince")));
            car.setCharCity(cursor.getString(cursor.getColumnIndex("charcity")));
            car.setCarNumberString(cursor.getString(cursor.getColumnIndex("carnumberstring")));
            car.setEnginenumber(cursor.getString(cursor.getColumnIndex("enginenumber")));
            car.setChejianumber(cursor.getString(cursor.getColumnIndex("chejianumber")));
        }
        return car;
    }

    public int getCount() {
        int count = 0;
        String countQuery = "select * from" + "cardetailtable";
        Cursor cursor = db.rawQuery(countQuery, null);
        if (cursor != null && !cursor.isClosed()) {
            count = cursor.getCount();
            cursor.close();
        }
        return count;
    }

    public void removelist(String shortprovince, String charcity, String carnumberstring) {
        String countQuery = "delete from " + "cardetailtable " + "where " + "shortprovince=" + "'" + shortprovince + "'" + " AND " + "charcity=" + "'" + charcity + "'" + " AND " + "carnumberstring=" + "'" + carnumberstring + "'";
//        String countQuery  = db.delete("cardetailtable",)
        db.execSQL(countQuery);
    }

    public List<CarAccidentDetail> queryAccident(String hphm) {
        List<CarAccidentDetail> list = new ArrayList<CarAccidentDetail>();

        Cursor cursor = db.rawQuery("select * from caraccidenttable where hphm = ?", new String[]{hphm});
        if (cursor.moveToFirst())
            do {
                CarAccidentDetail carAccidentDetail = new CarAccidentDetail();
                carAccidentDetail.setData(cursor.getString(cursor.getColumnIndex("data")));
                carAccidentDetail.setArea(cursor.getString(cursor.getColumnIndex("area")));
                carAccidentDetail.setAct(cursor.getString(cursor.getColumnIndex("act")));
                carAccidentDetail.setCode(cursor.getString(cursor.getColumnIndex("code")));
                carAccidentDetail.setFen(cursor.getString(cursor.getColumnIndex("fen")));
                carAccidentDetail.setMoney(cursor.getString(cursor.getColumnIndex("money")));
                carAccidentDetail.setHandled(cursor.getString(cursor.getColumnIndex("handled")));
                list.add(carAccidentDetail);


            } while (cursor.moveToNext());
        return list;
    }

    public CityDetail queryCity(String city_name) {
        CityDetail city = new CityDetail();
        Cursor cursor = db.rawQuery("select * from provincecitytable where city_name = ?", new String[]{city_name});
        if (cursor.moveToFirst()) {
            city.setProvince(cursor.getString(cursor.getColumnIndex("province")));
            city.setProvince_code(cursor.getString(cursor.getColumnIndex("province_code")));
            city.setCity_name(cursor.getString(cursor.getColumnIndex("city_name")));
            city.setCity_code(cursor.getString(cursor.getColumnIndex("city_code")));
            city.setAbbr(cursor.getString(cursor.getColumnIndex("abbr")));
            city.setEngine(cursor.getString(cursor.getColumnIndex("engine")));
            city.setEngineno(cursor.getString(cursor.getColumnIndex("engineno")));
            city.setClassa(cursor.getString(cursor.getColumnIndex("classa")));
            city.setClassno(cursor.getString(cursor.getColumnIndex("classno")));
            city.setRegist(cursor.getString(cursor.getColumnIndex("regist")));
            city.setRegistno(cursor.getString(cursor.getColumnIndex("registno")));
        }
        cursor.close();
        return city;


    }
}
