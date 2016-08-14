package com.example.avinash.app;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import async.AsyncResult;
import async.LoginAsync;

public class LoginActivity extends AppCompatActivity implements AsyncResult {

    TextView txtStatus;
    Context context;
    SharedPreferences prefs;
    AsyncResult listener = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final TextView txtUsr = (TextView) findViewById(R.id.txtUsr);
        final TextView txtPwd = (TextView) findViewById(R.id.txtPwd);
        txtStatus = (TextView) findViewById(R.id.txtStatus);
        context = getApplicationContext();
        prefs = getSharedPreferences("MyPrefs",MODE_PRIVATE);
        checkLogin();
        if(this instanceof AsyncResult)
            listener = (AsyncResult)this;

        Button btnLogin = (Button) findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                LoginAsync asyncLogin = new LoginAsync(getApplicationContext(),listener,txtStatus);
                String usr = ""+txtUsr.getText();
                String pwd = ""+txtPwd.getText();
                asyncLogin.execute(usr,pwd);
            }
        });
    }
    public void checkLogin(){
        if(prefs.contains("usr")) {
            Intent mainIntent = (Intent) new Intent(context, MainActivity.class);
            startActivity(mainIntent);
            finish();
        }
    }

    @Override
    public void onComplete() {
        checkLogin();
    }
}
