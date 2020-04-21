package rishabhgupta.appdemo.pizzazia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.LinkedHashMap;
import java.util.Map;


public class Database extends SQLiteOpenHelper {
    private static final String DATABASE_NAME="Students.db";
    private static final String TABLE_NAME="Student_Table";
    private static final String Col_1="ID";
    private static final String Col_2="Username";
    private static final String Col_3="Password";
    public static Cursor res;
    public static LinkedHashMap<String,String> map = new LinkedHashMap<>();

    public Database(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table "+TABLE_NAME+"("+Col_1+" INTEGER PRIMARY KEY AUTOINCREMENT,"+Col_2+" TEXT,"+Col_3+" TEXT )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public void insertData(String username,String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Col_2,username);
        contentValues.put(Col_3,password);

        db.insert(TABLE_NAME, null, contentValues);
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        res = db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }

    public LinkedHashMap<String,String> verifyData() {
        SQLiteDatabase db = this.getWritableDatabase();
        res = db.rawQuery("select * from "+TABLE_NAME,null);
        while(res.moveToNext()) {
            map.put(res.getString(1), res.getString(2));
        }
        return map;
    }
}
