package com.im.origin.im.ui;

import android.os.Bundle;

import com.im.origin.R;
import com.im.origin.base.BaseActivity;


public class LoginActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);

    }
}
