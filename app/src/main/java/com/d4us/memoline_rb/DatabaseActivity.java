package com.d4us.memoline_rb;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.graphics.Bitmap;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.sql.Blob;

/**
 * Created by BlueBear on 2016-11-01.
 */
public class DatabaseActivity {
    // DB관련 상수 선언
    private static final String dbName = "memoline.db";
    private static final String tableName = "memo";
    public static final int dbVersion = 1;
    public static final String TAG = "DatabaseActivity";
    Blob b;
    // DB관련 객체 선언
    private OpenHelper opener; // DB opener
    private SQLiteDatabase db; // DB controller

    // 부가적인 객체들
    private Context context;
    int no;

    // 생성자 함수 : DatabaseActivity()
    public DatabaseActivity(Context context) {
        this.context = context;
        this.opener = new OpenHelper(context, dbName, null, dbVersion);
        db = opener.getWritableDatabase();
    }

    // Opener of DB and Table
    public class OpenHelper extends SQLiteOpenHelper {

        public OpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,
                          int version) {
            super(context, name, null, version);
            // TODO Auto-generated constructor stub
        }

        // 생성된 DB가 없을 경우에 한번만 호출됨
        @Override
        public void onCreate(SQLiteDatabase arg0) {
            String dropSql = "drop table if exists " + tableName;
            try {
                db.execSQL(dropSql);
            } catch(Exception ex) {
                Log.e(TAG, "Exception in DROP_SQL", ex);
            }

            String createSql = "create table " + tableName + "("
                    + "  _id integer primary key autoincrement, "
                    + "  no integer, "
                    + "  text text default ' ' , "
                    + "  bg text, "
                    + "  font text"
                    + ")";

            String deleteSql = "delete from " + tableName;
            try{

                arg0.execSQL(createSql);
                no = 1;

            } catch (Exception e){
                Log.e("오류", "Create_SQL 에서 오류가 났쪄염", e);
            }

        }

        @Override
        public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
            // TODO Auto-generated method stub
        }
    }



    // 데이터 추가
    public void insertData(int no, String text, byte[] bg, String font) {


        SQLiteStatement p = db.compileStatement("insert into " + tableName + "(no, text, bg, font) values(?, ?, ?, ?)");


        p.bindString(4,font);
        p.bindBlob(3, bg);
        p.bindString(2, text);
        p.bindDouble(1,no);
        p.execute();


    }

    // 데이터 수정
    public void updateData(String text, String bg, int index) {
        String sql = "update " + tableName + " set text = '" + text + "', bg = '" + bg + "' where id = " + index + ";";
        db.execSQL(sql);
    }

    // 데이터 삭제
    public void removeData(int index) {
        String sql = "delete from " + tableName + " where id = " + index + ";";
        db.execSQL(sql);
    }

    public byte[] getByteArrayFromBitmap(Bitmap d) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        d.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] data = stream.toByteArray();

        return data;
    }
 /*
    // 데이터 검색
    public APinfo selectData(int index) {
        String sql = "select * from " + tableName + " where id = " + index
                + ";";
        Cursor result = db.rawQuery(sql, null);

        // result(Cursor 객체)가 비어 있으면 false 리턴
        if (result.moveToFirst()) {
            APinfo info = new APinfo(result.getInt(0), result.getString(1),
                    result.getInt(2), result.getString(3));
            result.close();
            return info;
        }
        result.close();
        return null;
    }*/

}
