package com.example.avinash.app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import async.LoginAsync;

public class LoginActivity extends AppCompatActivity {

    TextView txtStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final TextView txtUsr = (TextView) findViewById(R.id.txtUsr);
        final TextView txtPwd = (TextView) findViewById(R.id.txtPwd);
        txtStatus = (TextView) findViewById(R.id.txtStatus);
        Button btnLogin = (Button) findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                LoginAsync asyncLogin = new LoginAsync(getApplicationContext(),txtStatus);
                String usr = ""+txtUsr.getText();
                String pwd = ""+txtPwd.getText();
                asyncLogin.execute(usr,pwd);
            }
        });
    }
}
