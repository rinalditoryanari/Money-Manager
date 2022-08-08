package com.example.toolbar;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

public class TransactionFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    ListView listView;
    DatabaseHelper databaseHelper;

    private String mParam1;
    private String mParam2;

    public TransactionFragment() {
    }


    public static TransactionFragment newInstance(String param1, String param2) {
        TransactionFragment fragment = new TransactionFragment();
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
        View v = inflater.inflate(R.layout.activity_transaction, container, false);
        TextView Name = v.findViewById(R.id.username);
        listView = v.findViewById(R.id.lv_money);
        Name.setText(Preferences.getLoggedInUser(getActivity().getBaseContext()));
        return v;
    }



    @Override
    public void onActivityCreated (@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        databaseHelper = new DatabaseHelper(getContext());
        ViewList(databaseHelper);
        List<DataModel> dataModels = databaseHelper.retrieveAll();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DataModel dataModel = (DataModel) parent.getItemAtPosition(position);
                Intent intent = new Intent(getContext(), UpdateDeleteMenu.class);
                intent.putExtra("id", String.valueOf(dataModel.getID()));
                intent.putExtra("jenis",dataModel.getJenis_transaction());
                intent.putExtra("tanggal",String.valueOf(dataModel.getTanggal()));
                intent.putExtra("nama", dataModel.getNama_transaction());
                intent.putExtra("harga", String.valueOf(dataModel.getJumlah_nominal()));

                startActivity(intent);
            }
        });


    }

    @Override
    public void onResume() {
        super.onResume();
        ViewList(databaseHelper);
    }

    private void ViewList(DatabaseHelper databaseHelper2) {
        ListViewAdapter listViewAdapter = new ListViewAdapter(getContext(), databaseHelper2.retrieveAll());
        listView.setAdapter(listViewAdapter);
    }
}


