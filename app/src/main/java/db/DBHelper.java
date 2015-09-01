package db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2015/8/11.
 */
public class DBHelper extends SQLiteOpenHelper {
    private static final String CAR_PLATE = "create table cardetailtable(" + "_id integer primary key autoincrement, "
            + "province text, " + "city text, " + "shortprovince text, " + "charcity text, " +
            "carnumberstring text, " + "enginenumber text, " + "chejianumber text" + "realid integer)";
    private static final String PROVINCE_CITY = "create table provincecitytable(" + "_id integer primary key autoincrement, " + "province text, " + "province_code text, " + "city_name text, " + "city_code text, " + "abbr text, " + "engine text, " + "engineno text, " + "classa text, " + "classno text, " + "regist text, " + "registno text)";
    private static final String CAR_ACCIDENT ="create table caraccidenttable(" + "_id integer primary key autoincrement, "
            +"province text, "
            +"city text, "
            +"hphm text, "
            +"hpzl text, "
            +"data text, "
            +"area text, "
            +"act text, "
            +"code text, "
            +"fen text, "
            +"money text, "
            +"handled text)";


    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CAR_PLATE);
        db.execSQL(PROVINCE_CITY);
        db.execSQL(CAR_ACCIDENT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
