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


public class DataBaseHelper extends SQLiteOpenHelper {

    private static String DB_PATH = "/data/data/com.dotgears.berkshire/databases/";

    private static String DB_NAME = "customer.db";

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

    public static final String CATE_KEY_CUSTOMERID = "customerId";
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

    // Search By Id

    public Customer getCustomerById(int id) {
        openDataBase();
        Cursor cursor = myDataBase.query(tblCustomers, new String[]{
                        CATE_KEY_CUSTOMERID, CATE_KEY_CUSTOMERNAME, CATE_KEY_CUSTOMEREMAIL,
                        CATE_KEY_CUSTOMERPHONE,CATE_KEY_CUSTOMERAVATAR}, CATE_KEY_CUSTOMERID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Customer customer = new Customer(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2), cursor.getString(3),cursor.getString(4));
        close();
        return customer;
    }

    // Search All

    public List<Customer> getAllCustomer() {
        openDataBase();
        List<Customer> allCustomer = new ArrayList<Customer>();
        String selectQuery = "SELECT  * FROM " + tblCustomers;

        Cursor cursor = myDataBase.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Customer customer = new Customer();
                customer.setCustomerID(Integer.parseInt(cursor.getString(0)));
                customer.setCustomerName(cursor.getString(1));
                customer.setCustomerEmail(cursor.getString(2));
                customer.setCustomerPhone(cursor.getString(3));
                customer.setCustomerAvatar(cursor.getString(4));
                allCustomer.add(customer);
            } while (cursor.moveToNext());
        }
        close();
        return allCustomer;
    }

    // List Cursor

    public Cursor ListCursor(String selectQuery) {
        openDataBase();
        Cursor cursor1 = myDataBase.rawQuery(selectQuery, null);
        close();
        return cursor1;
    }

    // Update Customer

    public int updateCustomer(Customer customer) {
        openDataBase();
        ContentValues values = new ContentValues();
        values.put(CATE_KEY_CUSTOMERNAME, customer.getCustomerName());
        values.put(CATE_KEY_CUSTOMEREMAIL, customer.getCustomerEmail());
        values.put(CATE_KEY_CUSTOMERPHONE, customer.getCustomerPhone());
        values.put(CATE_KEY_CUSTOMERAVATAR, customer.getCustomerAvatar());
        values.put(CATE_KEY_CUSTOMERID, customer.getCustomerID());

        close();
        return myDataBase.update(tblCustomers, values, CATE_KEY_CUSTOMERID + " = ?",
                new String[]{String.valueOf(customer.getCustomerID())});
    }

    // Delete Customer

    public void deleteCustomer(Customer customer) {
        openDataBase();
        myDataBase.delete(tblCustomers, CATE_KEY_CUSTOMERID + " = ?",
                new String[]{String.valueOf(customer.getCustomerID())});
        close();
    }

    // Delete All Customer

    public void deleteAllCustomer() {
        openDataBase();
        String sQuery = "DELETE FROM " + tblCustomers;
        myDataBase.execSQL(sQuery);
        close();
    }

    public int getCustomerCount() {
        openDataBase();
        String countQuery = "SELECT  * FROM " + tblCustomers;
        Cursor cursor = myDataBase.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();
        close();
        return count;
    }
}