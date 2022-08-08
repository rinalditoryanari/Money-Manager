package com.example.toolbar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class UpdateDeleteMenu extends Activity {
    EditText nama;
    EditText harga;
    String jenis;
    String tanggal;
    Button update, delete;
    DatabaseHelper databaseHelper = new DatabaseHelper(this);
    DataModel dataModel;
    int newid;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Update/Delete Menu");
        setContentView(R.layout.activity_edittrans);
        nama = findViewById(R.id.ed_nameTrans);
        harga = findViewById(R.id.ed_moneyTrans);
        update = findViewById(R.id.save_addtrans);
        delete = findViewById(R.id.btn_hapusTrans);

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        newid = Integer.parseInt(id);
        jenis = intent.getStringExtra("jenis");
        tanggal = intent.getStringExtra("date");
        nama.setText(intent.getStringExtra("nama"));
        harga.setText(intent.getStringExtra("harga"));


        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(UpdateDeleteMenu.this,String.valueOf(tanggal), Toast.LENGTH_LONG).show();
                //dataModel = new DataModel(newid, tanggal, Integer.parseInt(jenis), nama.getText().toString(), Double.parseDouble(harga.getText().toString()));
                boolean b = databaseHelper.update(newid,nama.getText().toString(),Double.parseDouble(harga.getText().toString()));
                if(b) {
                    Toast.makeText(UpdateDeleteMenu.this, "Data Updated!", Toast.LENGTH_SHORT).show();
                    Intent intent1 = new Intent(UpdateDeleteMenu.this, TransactionFragment.class);
                    intent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    finish();
                } else {
                    Toast.makeText(UpdateDeleteMenu.this, "Update Failed!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //dataModel = new DataModel(newid, tanggal, Integer.parseInt(jenis), nama.getText().toString(), Double.parseDouble(harga.getText().toString()));
                boolean b = databaseHelper.delete(newid);
                if(b) {
                    Toast.makeText(UpdateDeleteMenu.this, "Data Deleted!", Toast.LENGTH_SHORT).show();
                    Intent intent1 = new Intent(UpdateDeleteMenu.this, TransactionFragment.class);
                    intent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    finish();
                } else {
                    Toast.makeText(UpdateDeleteMenu.this, "Delete Failed!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
