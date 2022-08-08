package com.example.toolbar;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;


public class DatabaseHelper extends SQLiteOpenHelper {
    private DatabaseHelper databaseHelper;
    private Context context;
    private SQLiteDatabase database;
    public static final String MONEY_TABLE = "MONEY_TABLE";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_TANGGAL = "TANGGAL";
    public static final String COLUMN_JENIS_TRANSACTION = "JENIS_TRANSACTION";
    public static final String COLUMN_NAMA_TRANSACTION = "NAMA_TRANSACTION";
    public static final String COLUMN_JUMLAH_NOMINAL = "JUMLAH_NOMINAL";


    public DatabaseHelper(Context context) {
        super(context, "money.db", null, 1);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + MONEY_TABLE + " ("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_TANGGAL + " TEXT, "
                + COLUMN_JENIS_TRANSACTION + " INT, "
                + COLUMN_NAMA_TRANSACTION + " TEXT, "
                + COLUMN_JUMLAH_NOMINAL + " INT);";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean AddTransaction(DataModel dataModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_JENIS_TRANSACTION, dataModel.getJenis_transaction());
        cv.put(COLUMN_TANGGAL, dataModel.getTanggal());
        cv.put(COLUMN_NAMA_TRANSACTION, dataModel.getNama_transaction());
        cv.put(COLUMN_JUMLAH_NOMINAL, dataModel.getJumlah_nominal());
        long insert = db.insert(MONEY_TABLE, null, cv);
        if (insert == -1) {
            return false;
        } else {
            return true;
        }
    }

    public List<DataModel> retrieveAll() {
        List<DataModel> list = new ArrayList<>();
        String retrieve = "SELECT * FROM " + MONEY_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(retrieve, null);
        if(cursor.moveToFirst()) {
            do {
                int ID = cursor.getInt(0);
                int tanggal = cursor.getInt(1);
                String jenisTrans = cursor.getString(2);
                String nama = cursor.getString(3);
                int jumlah = cursor.getInt(4);
                DataModel data = new DataModel(ID,jenisTrans, tanggal, nama, jumlah);
                list.add(data);
            } while (cursor.moveToNext());
        } else {
        }
        cursor.close();
        db.close();
        return list;
    }

    public boolean update(int id,String nama,double harga) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAMA_TRANSACTION,nama) ;
        contentValues.put(COLUMN_JUMLAH_NOMINAL,harga);
        int i = db.update(DatabaseHelper.MONEY_TABLE, contentValues, COLUMN_ID + " = " + id, null);
        if ( i == -1 ) {
            return false;
        } else {
            return true;
        }
    }

    public boolean delete(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + MONEY_TABLE + " WHERE " + COLUMN_ID + " = " + id;

        Cursor cursor = db.rawQuery(query, null);
        if(!cursor.moveToFirst()) {
            return true;
        } else {
            return false;
        }
    }
}







