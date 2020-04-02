package com.edicionsoft.mvp.Activitys;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.edicionsoft.mvp.Model.Model;
import com.edicionsoft.mvp.Presenter.Presenter;
import com.edicionsoft.mvp.R;
import com.edicionsoft.mvp.View.View;

public class MainActivity extends AppCompatActivity implements View {
    Presenter mPresenter;
    private EditText username;
    private EditText password;
    private Button login;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        mPresenter = new Model(MainActivity.this);
        username = findViewById(R.id.login_nombre);
        password = findViewById(R.id.login_password);
        login = findViewById(R.id.login_btn_login);

        login.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                progressDialog = ProgressDialog.show(MainActivity.this, "Iniciando Sesión", "Espere un momento...", true, true);
                mPresenter.performLogin(username.getText().toString(),password.getText().toString());

            }
        });
    }

    @Override
    public void loginSucces(String key) {
        progressDialog.dismiss();
        Intent intent = new Intent(MainActivity.this, AlumnosActivity.class);
        intent.putExtra("key",key);
        Toast.makeText(getApplicationContext(),"Se inicio sesion",Toast.LENGTH_LONG).show();
        startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));

    }

    @Override
    public void loginError() {
        progressDialog.dismiss();
        Toast.makeText(getApplicationContext(),"Ocurrio un error",Toast.LENGTH_LONG).show();

    }
    @Override
    protected void onResume() {
        super.onResume();
        username.setText("");
        password.setText("");

    }



}
