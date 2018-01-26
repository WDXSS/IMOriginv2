package com.im.origin.login;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.im.origin.R;
import com.im.origin.base.BaseActivity;
import com.netease.nimlib.sdk.AbortableFuture;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.RequestCallback;
import com.netease.nimlib.sdk.auth.AuthService;
import com.netease.nimlib.sdk.auth.LoginInfo;
import com.netease.nimlib.sdk.uinfo.UserService;
import com.netease.nimlib.sdk.uinfo.model.NimUserInfo;


public class LoginActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = "LoginActivity";
    private EditText name;
    private EditText password;
    private Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);
        name = findViewById(R.id.name);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);
        login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login:
                LoginInfo loginInfo = new LoginInfo(name.getText().toString(), password.getText().toString());
                AbortableFuture<LoginInfo> abortableFuture = NIMClient.getService(AuthService.class).login(loginInfo);
                abortableFuture.setCallback(new RequestCallback<LoginInfo>() {
                    @Override
                    public void onSuccess(LoginInfo param) {
                        Log.i(TAG, "onSuccess: param = " + param);
                        Log.i(TAG, "onSuccess: " + param.getAccount());
                        NimUserInfo userInfo = NIMClient.getService(UserService.class).getUserInfo(param.getAccount());
                        Log.i(TAG, "onSuccess: userInfo.getName() = "+ userInfo.getName()
                        );
                    }

                    @Override
                    public void onFailed(int code) {
                        Log.i(TAG, "onFailed: code = " + code);
                    }

                    @Override
                    public void onException(Throwable exception) {

                    }
                });
                break;
        }
    }
}
