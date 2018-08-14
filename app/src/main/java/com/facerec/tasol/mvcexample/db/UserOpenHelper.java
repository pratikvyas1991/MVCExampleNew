package com.facerec.tasol.mvcexample.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.facerec.tasol.mvcexample.model.UserModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tasol on 14/8/18.
 */

public class UserOpenHelper extends SQLiteOpenHelper {

    private static final String DBNAME = "test_user_db";

    private static final int DBVERSION = 1;

    public UserOpenHelper(Context context) {
        super(context, DBNAME, null, DBVERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String usertable = "CREATE TABLE IF NOT EXISTS usertable (useremail TEXT PRIMARY KEY,username TEXT,userage TEXT)";
        db.execSQL(usertable);
    }

    public void addUser(UserModel userModel){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("useremail", userModel.getmUserEmail());
        contentValues.put("username", userModel.getmUserName());
        contentValues.put("userage", userModel.getmUserAge());
        database.insert("usertable", null, contentValues);
        database.close();
    }

    public List<UserModel> getAllUsers(){
        List<UserModel> userList =new ArrayList<>();
        userList.clear();
        String selectQuery = "SELECT * FROM usertable";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        UserModel userModel;
        if (cursor.moveToFirst()) {
            do {
                userModel = new UserModel();
                String email = cursor.getString(cursor.getColumnIndex("useremail"));
                String name = cursor.getString(cursor.getColumnIndex("username"));
                String age = cursor.getString(cursor.getColumnIndex("userage"));
                userModel.setmUserEmail(email);
                userModel.setmUserName(name);
                userModel.setmUserAge(age);
                userList.add(userModel);
            } while (cursor.moveToNext());
        }
        db.close();
        return userList;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
