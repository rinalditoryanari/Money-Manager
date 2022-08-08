package com.example.toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class SettingFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private EditText Username, Password;

    private String mParam1;
    private String mParam2;
    public SettingFragment() {
    }

    public static SettingFragment newInstance(String param1, String param2) {
        SettingFragment fragment = new SettingFragment();
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
        View v = inflater.inflate(R.layout.activity_pengaturan, container, false);
        v.findViewById(R.id.btn_signout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Preferences.clearLoggedInUser(getActivity().getBaseContext());
                startActivity(new Intent(getActivity().getBaseContext(), ActivityLogin.class));
                getActivity().finish();
            }
        });

        v.findViewById(R.id.btn_emailUs).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent email=new Intent(Intent.ACTION_SEND);
                email.setType("message/rfc822");
                email.putExtra(Intent.EXTRA_EMAIL, new String[]{"kharisismail56@gmail.com"});
                email.putExtra(Intent.EXTRA_SUBJECT, "Mail from User");
                email.putExtra (Intent.EXTRA_TEXT, "Hi Developer, I'm your user") ;
                startActivity(Intent.createChooser(email, "Choose Mail App"));
            }
        });


        v.findViewById(R.id.btn_edit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View m) {
                Username = v.findViewById(R.id.username);
                Password = v.findViewById(R.id.password);
                String user = Username.getText().toString();
                String password = Password.getText().toString();
                Preferences.setRegisteredUser(getActivity().getBaseContext(), user);
                Preferences.setRegisteredPass(getActivity().getBaseContext(), password);
                Toast toast = Toast.makeText(getActivity().getApplicationContext(),
                        "Update Successfully",
                        Toast.LENGTH_SHORT);
                toast.show();
                Preferences.clearLoggedInUser(getActivity().getBaseContext());
                startActivity(new Intent(getActivity().getBaseContext(), ActivityLogin.class));
                getActivity().finish();

            }
        });

        return v ;
    }


    }

