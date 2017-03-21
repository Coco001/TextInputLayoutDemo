package com.coco.textinputlayoutdemo;

import android.content.Context;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.Toast;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {
    private Button mLogbt;
    private TextInputLayout mUsernameWrapper;
    private TextInputLayout mPasswordWrapper;
    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9#_~!$&'()*+,;=:.\"(),:;<>@\\[\\]\\\\]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*$";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        initEvent();
    }

    private void initEvent() {
        mLogbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKeyboard();
                String username = mUsernameWrapper.getEditText().getText().toString();
                String password = mPasswordWrapper.getEditText().getText().toString();
                if (!validateEmail(username)) {
                    mUsernameWrapper.setError("Not a valid email address!");
                } else if (!validatePassword(password)) {
                    mPasswordWrapper.setError("Not a valid password!");
                } else {
                    mUsernameWrapper.setErrorEnabled(false);
                    mPasswordWrapper.setErrorEnabled(false);
                    doLogin();
                }
            }
        });
    }

    /**
     * 登录
     */
    private void doLogin() {
        Toast.makeText(getApplicationContext(), "OK! I'm performing login.", Toast.LENGTH_SHORT).show();
    }

    private void initView() {
        mUsernameWrapper = (TextInputLayout) findViewById(R.id.usernameWrapper);
        mPasswordWrapper = (TextInputLayout) findViewById(R.id.passwordWrapper);
        mLogbt = (Button) findViewById(R.id.btn);
        mUsernameWrapper.setHint("Username");
        mPasswordWrapper.setHint("Password");
    }


    /**
     * 匹配邮箱的方法
     * @param email 传入字符串
     * @return 是不是一个合法的邮箱
     */
    public boolean validateEmail(String email) {
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    /**
     * 对密码进行验证
     * @param password 传入密码的字符串
     * @return 是否符合密码的长度要求
     */
    public boolean validatePassword(String password) {
        return password.length() > 5;
    }

    /**
     * 隐藏键盘的方法
     */
    private void hideKeyboard() {
        View view = getCurrentFocus();
        if (view != null) {
            ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).
                    hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

}
