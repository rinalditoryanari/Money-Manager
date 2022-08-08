package com.example.toolbar;

import androidx.annotation.Nullable;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DataModel {
    private int Id;
    private String jenis_transaction;
    private int tanggal;
    private String nama_transaction;
    private double jumlah_nominal;

    public DataModel(int Id, String jenis_transaction, @Nullable int tanggal, String nama_transaction, double jumlah_nominal) {
        this.Id = Id;
        this.tanggal = tanggal;
        this.jenis_transaction = jenis_transaction;
        this.nama_transaction = nama_transaction;
        this.jumlah_nominal = jumlah_nominal;

    }

    public DataModel() {
    }

    @Override
    public String toString() {
        return "DataModel{" +
                "Id=" + Id +
                ", jenis_transaction='" + jenis_transaction + '\'' +
                ", tanggal=" + tanggal +
                ", nama_transaction='" + nama_transaction + '\'' +
                ", jumlah_nominal=" + jumlah_nominal +
                '}';
    }

    public int getID() {
        return Id;
    }

    public void setID(int Id) {
        this.Id = Id;
    }

    public int getTanggal() {
        return tanggal;
    }

    public void setTanggal(int tanggal){
        this.tanggal = tanggal;
    }

    public String getJenis_transaction() {
        return jenis_transaction;
    }

    public String getNama_transaction(){
        return nama_transaction;
    }

    public double getJumlah_nominal(){
        return jumlah_nominal;
    }

    public void setNama_transaction(String nama_transaction){
        this.nama_transaction = nama_transaction;
    }

    public void setJumlah_nominal(double jumlah_nominal){
        this.jumlah_nominal = jumlah_nominal;
    }

    public void setJenis_transaction(String jenis_transaction){
        this.jenis_transaction=jenis_transaction;
    }

}


