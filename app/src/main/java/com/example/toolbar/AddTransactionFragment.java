package com.example.toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.text.DateFormat;

public class AddTransactionFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private EditText Jenis,Nama,Jumlah,Tanggal;

    private String mParam1;
    private String mParam2;
    public AddTransactionFragment() {
    }

    public static AddTransactionFragment newInstance(String param1, String param2) {
        AddTransactionFragment fragment = new AddTransactionFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.activity_addtrans, container, false);
        DatabaseHelper databaseHelper = new DatabaseHelper(getContext());
        Jenis = v.findViewById(R.id.trans);
        Tanggal = v.findViewById(R.id.ed_dateTrans);
        Nama = v.findViewById(R.id.ed_nameTrans);
        Jumlah = v.findViewById(R.id.ed_moneyTrans);
        v.findViewById(R.id.save_addtrans).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String jenis = Jenis.getText().toString();
                    String nama = Nama.getText().toString();
                    int tanggal = Integer.parseInt(Tanggal.getText().toString());
                    double jumlah = Double.parseDouble(Jumlah.getText().toString());
                    DataModel data = new DataModel(-1,jenis,tanggal, nama, jumlah);
                    boolean valid = databaseHelper.AddTransaction(data);
                    if (valid == true) {
                        Toast.makeText(getActivity().getApplicationContext(), "Data Berhasil Ditambahkan", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getActivity().getApplicationContext(), "Data Gagal Ditambahkan", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    Toast.makeText(getActivity().getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });




        return v;
    }




}
