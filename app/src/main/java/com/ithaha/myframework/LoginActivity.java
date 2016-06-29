package com.ithaha.myframework;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.ithaha.myframework.base.BaseActivity;
import com.ithaha.myframework.base.MyApplication;

import butterknife.Bind;

public class LoginActivity extends BaseActivity {

    @Bind(R.id.login)
    Button login;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        MyApplication.sAppStatus = 0;
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void setUpContentView() {
        setContentView(R.layout.activity_login, R.string.login);

    }

    @Override
    protected void initView() {
        super.initView();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyApplication.sMyData.add("TestNullPointer");
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }


}
