package com.example.toolbar;

import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import static android.text.Html.fromHtml;

public class ActivityLogin extends AppCompatActivity {
    private EditText Username, Password;
    private WifiManager wifiManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        reg();

        Username=findViewById(R.id.et_email_SignIN);
        Password=findViewById(R.id.et_pass_SignIN);

        Password.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_NULL){
                    cekinput();
                    return true;
                }
                return false;
            }
        });
        findViewById(R.id.button_signin_SignIN).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cekinput();
            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        if(Preferences.getLoggedInStatus(getBaseContext())){
            startActivity(new Intent(getBaseContext(),MainActivity.class));
            finish();
        }
    }
    private void reg(){
        TextView textViewCreateAccount = (TextView) findViewById(R.id.signup_tv);
        textViewCreateAccount.setText(fromHtml("<font color='#ffffff'>I don't have account yet. </font><font color='#0c0099'>create one</font>"));
        textViewCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityLogin.this, ActivityRegister.class);
                startActivity(intent);
            }
        });
    }
    private void cekinput(){
        Username.setError(null);
        Password.setError(null);
        View fokus = null;
        boolean kosong  = false;

        String user = Username.getText().toString();
        String password = Password.getText().toString();
        if (TextUtils.isEmpty(user)){
            Username.setError("ISI WOYY");
            fokus = Username;
            kosong = true;
        } else if (!cek_User(user)){
            Username.setError("Username gak ada");
            fokus = Username;
            kosong = true;
        }
        if (TextUtils.isEmpty(password)){
            Password.setError("ISI WOYY");
            fokus = Password;
            kosong = true;
        } else if (!cek_Password(password)){
            Password.setError("Pass gak ada");
            fokus = Password;
            kosong = true;
        }
        if (kosong == true){
            fokus.requestFocus();
        }
        else {
            pindah();
        }
    }
    private boolean cek_Password(String password){
        return password.equals(Preferences.getRegisteteredPass(getBaseContext()));
    }
    private boolean cek_User(String user){
        return user.equals(Preferences.getRegisteredUser(getBaseContext()));
    }
    private void pindah(){
        Preferences.setLoggedInUser(getBaseContext(),Preferences.getRegisteredUser(getBaseContext()));
        Preferences.setloggedInStatus(getBaseContext(),true);
        startActivity(new Intent(getBaseContext(),MainActivity.class));
        finish();;
    }
}
