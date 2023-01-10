package com.example.myapplication;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DBHelper  extends SQLiteOpenHelper {

private static final String DATABASE_NAME="db.sqlite3";
private static final int database_version=1;
private final Context context;
SQLiteDatabase db;

    private static final String DATABASE_PATH = "/data/data/com.example.myapplication/databases/";
    private final  String STUDENT_TABLE="Login_student";

    public DBHelper(Context context) {

        super(context, DATABASE_NAME, null, database_version);
        this.context= context;
        createDb();


    }

    @Override
    public void onCreate(SQLiteDatabase db){

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
    if (newVersion>oldVersion)
        copyDatabase();
    }

    public void createDb() {
        boolean dbExist = checkDbExist();

        if(!dbExist){
            this.getReadableDatabase();
            copyDatabase();
        }

    }

    private boolean checkDbExist(){
        SQLiteDatabase sqLiteDatabase = null;

        try {
            String path = DATABASE_PATH + DATABASE_NAME;
            sqLiteDatabase = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READONLY);
        }catch (Exception ex){
        }



        if(sqLiteDatabase != null){
            sqLiteDatabase.close();
            return true;
        }
        return false;
    }

    private void  copyDatabase() {
        try {
            InputStream inputStream = context.getAssets().open(DATABASE_NAME);

            String outFileName = DATABASE_PATH + DATABASE_NAME;

            OutputStream outputStream = new FileOutputStream(outFileName);

            byte [] b = new byte[1024];
            int length;

            while ((length = inputStream.read(b))> 0){
                outputStream.write(b, 0, length);
            }

            outputStream.flush();
            outputStream.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private SQLiteDatabase openDatabase(){
        String path = DATABASE_PATH + DATABASE_NAME;
        db= SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READONLY);
        return db;
    }

    public void close(){
        if (db !=null) {
            db.close();
        }
    }

    public boolean checkUserExist(String name, String id){
        String[] columns = {"username"};
        db = openDatabase();

        String selection = "name=? and  id= ?";
        String[] selectionArgs = {name, id};

        Cursor cursor = db.query(STUDENT_TABLE, columns, selection, selectionArgs, null, null, null);
        int count = cursor.getCount();

        cursor.close();
        close();

        if(count > 0){
            return true;
        }else{
            return false;
        }
    }
}