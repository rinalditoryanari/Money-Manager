package com.example.toolbar;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import static android.text.Html.fromHtml;

public class ActivityRegister  extends AppCompatActivity {
    private EditText Username, Password, Repassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        log();

        Username=findViewById(R.id.et_emailSignun);
        Password=findViewById(R.id.et_passwordSignun);
        Repassword = findViewById(R.id.et_passwordSignun2);

        Repassword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_NULL){
                    cekinput();
                    return true;
                }
                return false;
            }
        });
        findViewById(R.id.button_signunSignun).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cekinput();
            }
        });
    }
    public void cekinput(){
        Username.setError(null);
        Password.setError(null);
        Repassword.setError(null);
        View fokus = null;
        boolean kosong = false;
        String user = Username.getText().toString();
        String password = Password.getText().toString();
        String repassword = Repassword.getText().toString();
        if (TextUtils.isEmpty(user)){
            Username.setError("Mohon diisi");
            fokus = Username;
            kosong = true;
        } else if (cekUser(user)){
            Username.setError("Username telah ada");
            fokus = Username;
            kosong = true;
        }
        if (TextUtils.isEmpty(password)){
            Password.setError("Mohon Diisi");
            fokus = Password;
            kosong = true;
        } else if (!cekpassword(password, repassword)){
            Repassword.setError("Password salah");
            fokus = Repassword;
            kosong = true;
        }
        if (kosong == true){
            fokus.requestFocus();
        }
        else {
            Preferences.setRegisteredUser(getBaseContext(),user);
            Preferences.setRegisteredPass(getBaseContext(),password);
            finish();
        }
    }
    private void log() {
        TextView textViewLogin = (TextView) findViewById(R.id.login_tv);
        textViewLogin.setText(fromHtml("<font color='#ffffff'>Have Account ? </font><font color='#0c0099'>Login !!</font>"));
        textViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    private boolean cekpassword(String password, String repassword){
        return password.equals(repassword);
    }

    private boolean cekUser(String user){
        return user.equals(Preferences.getRegisteredUser(getBaseContext()));
    }
}
