package com.dotgears.berkshire.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import com.dotgears.berkshire.model.Customer;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import fsoft.fwa.elearing.model.User;

public class DataBaseHelper extends SQLiteOpenHelper {

    private static String DB_PATH = "/data/data/fsoft.fwa.elearing/databases/";

    private static String DB_NAME = "user.db";

    private SQLiteDatabase myDataBase;

    private final Context myContext;

    public DataBaseHelper(Context context) {

        super(context, DB_NAME, null, 1);
        this.myContext = context;
    }

    public void createDataBase() throws IOException {
        boolean dbExist = checkDataBase();
        if (dbExist) {
        } else {
            this.getReadableDatabase();
            try {
                copyDataBase();
            } catch (IOException e) {
                throw new Error("Error copying database");
            }
        }

    }

    private boolean checkDataBase() {
        SQLiteDatabase checkDB = null;
        try {
            String myPath = DB_PATH + DB_NAME;
            checkDB = SQLiteDatabase.openDatabase(myPath, null,
                    SQLiteDatabase.OPEN_READWRITE);
        } catch (SQLiteException e) {
        }
        if (checkDB != null) {
            checkDB.close();
        }
        return checkDB != null ? true : false;
    }

    private void copyDataBase() throws IOException {
        AssetManager assetManager = myContext.getResources().getAssets();
        String[] str = assetManager.list("");
        InputStream myInput = myContext.getAssets().open(DB_NAME);
        String outFileName = DB_PATH + DB_NAME;
        OutputStream myOutput = new FileOutputStream(outFileName);
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }
        myOutput.flush();
        myOutput.close();
        myInput.close();
    }

    public void openDataBase() throws SQLException {
        String myPath = DB_PATH + DB_NAME;
        myDataBase = SQLiteDatabase.openDatabase(myPath, null,
                SQLiteDatabase.OPEN_READWRITE);
    }

    @Override
    public synchronized void close() {

        if (myDataBase != null)
            myDataBase.close();
        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    // Customer Table

//    public static final String CATE_KEY_CUSTOMERID = "customerId";
    public static final String CATE_KEY_CUSTOMERNAME = "customerName";
    public static final String CATE_KEY_CUSTOMEREMAIL = "customerEmail";
    public static final String CATE_KEY_CUSTOMERPHONE = "customerPhone";
    public static final String CATE_KEY_CUSTOMERAVATAR = "customerAvatar";
    public static final String tblCustomers = "customer";

    // Add Customer

    public void addNewCustomer(Customer customer) {
        openDataBase();
        ContentValues values = new ContentValues();
        values.put(CATE_KEY_CUSTOMERNAME, customer.getCustomerName());
        values.put(CATE_KEY_CUSTOMEREMAIL, customer.getCustomerEmail());
        values.put(CATE_KEY_CUSTOMERPHONE, customer.getCustomerPhone());
        values.put(CATE_KEY_CUSTOMERAVATAR, customer.getCustomerAvatar());
        myDataBase.insert(tblCustomers, null, values);
        close();
    }

    public User getUsert(int id) {
        openDataBase();
        Cursor cursor = myDataBase.query(tblusers, new String[]{
                        CATE_KEY_USERID, CATE_KEY_FULLNAME, CATE_KEY_EMAIL,
                        CATE_KEY_AVATAR}, CATE_KEY_USERID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        User user = new User(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2), cursor.getString(3));
        close();
        return user;
    }

    public List<User> getAllUser() {
        openDataBase();
        List<User> allUser = new ArrayList<User>();
        String selectQuery = "SELECT  * FROM " + tblusers;

        Cursor cursor = myDataBase.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                User users = new User();
                users.setUserID(Integer.parseInt(cursor.getString(1)));
                users.setFullName(cursor.getString(2));
                users.setEmail(cursor.getString(3));
                users.setAvatar(cursor.getString(4));
                allUser.add(users);
            } while (cursor.moveToNext());
        }
        close();
        return allUser;
    }

    public Cursor ListCursor(String selectQuery) {
        openDataBase();
        Cursor cursor1 = myDataBase.rawQuery(selectQuery, null);
        close();
        return cursor1;
    }

    public int updateUser(User user) {
        openDataBase();
        ContentValues values = new ContentValues();
        values.put(CATE_KEY_FULLNAME, user.getFullName());
        values.put(CATE_KEY_EMAIL, user.getEmail());
        values.put(CATE_KEY_AVATAR, user.getAvatar());
        values.put(CATE_KEY_USERID, user.getUserID());

        close();
        return myDataBase.update(tblusers, values, CATE_KEY_USERID + " = ?",
                new String[]{String.valueOf(user.getUserID())});
    }

    public void deleteUser(User user) {
        openDataBase();
        myDataBase.delete(tblusers, CATE_KEY_USERID + " = ?",
                new String[]{String.valueOf(user.getUserID())});
        close();
    }

    public void deleteAllUser() {
        openDataBase();
        String sQuery = "DELETE FROM " + tblusers;
        myDataBase.execSQL(sQuery);
        close();
    }

    public int getUserCount() {
        openDataBase();
        String countQuery = "SELECT  * FROM " + tblusers;
        Cursor cursor = myDataBase.rawQuery(countQuery, null);
        int cnt = cursor.getCount();
        cursor.close();
        close();
        return cnt;
    }
}