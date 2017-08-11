package com.ptit.trongthien.vnexpress_v1.view.login_view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ptit.trongthien.vnexpress_v1.presenter.login_presenter.LoginPresenterImp;
import com.ptit.trongthien.vnexpress_v1.R;
import com.ptit.trongthien.vnexpress_v1.view.MainActivity;
import com.ptit.trongthien.vnexpress_v1.view.home_view.HomeActivity;

/**
 * Created by TrongThien on 7/21/2017.
 */

public class LoginFragment extends Fragment implements LoginView, View.OnClickListener {
    public static final String TAG_LOGIN = "LoginFragment";
    private EditText edtUsername, edtPassword;
    private Button btnLogin;
    private TextView tvRegister;
    private LoginPresenterImp loginPresenterImp;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_login, container, false);
        return view;

    }

    @Override
    public void onStart() {
        super.onStart();
        loginPresenterImp = new LoginPresenterImp(this, getActivity());
        initView();
    }

    private void initView() {
        edtUsername = (EditText) getActivity().findViewById(R.id.edtUsername);
        edtPassword = (EditText) getActivity().findViewById(R.id.edtPassword);
        btnLogin = (Button) getActivity().findViewById(R.id.btnLogin);
        tvRegister = (TextView) getActivity().findViewById(R.id.tvRegister);
        tvRegister.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
    }

    @Override
    public void loginSuccess() {
        Intent intent = new Intent(getActivity(), HomeActivity.class);
        startActivity(intent);
        getActivity().finish();
    }

    @Override
    public void loginFail() {
        Toast.makeText(getActivity(), "Fail to login", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view) {
        String username = edtUsername.getText().toString();
        String password = edtPassword.getText().toString();
        switch (view.getId()) {
            case R.id.tvRegister:
                MainActivity mainActivity = (MainActivity) getActivity();
                mainActivity.changeFragmentStack(mainActivity.getRegisterFragment());
                break;
            case R.id.btnLogin:
                if (username.equals("") || password.equals("")) {
                    Toast.makeText(getActivity(), "Chua nhap du thong tin", Toast.LENGTH_SHORT).show();
                    return;
                }
                loginPresenterImp.checkLogin(username, password);
                break;
        }

    }
}
